/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.promantus.hireprous.HireProUsConstants;
import com.promantus.hireprous.dto.IssueDto;
import com.promantus.hireprous.dto.SearchIssueDto;
import com.promantus.hireprous.entity.Issue;
import com.promantus.hireprous.repository.IssueRepository;
import com.promantus.hireprous.service.CommonService;
import com.promantus.hireprous.service.IssueService;
import com.promantus.hireprous.service.UserService;
import com.promantus.hireprous.util.HireProUsUtil;


@Service
public class IssueServiceImpl implements IssueService {
	
	private static final Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);
	
	@Autowired
	CommonService commonService;

	@Autowired
	UserService userService;

	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public IssueDto addIssue(final IssueDto issueDto, String lang) throws Exception {

		IssueDto resultDto = new IssueDto();

		Issue issue = new Issue();
		issue.setIssueId(commonService.nextSequenceNumber());
		issue.setIssueType(issueDto.getIssueType());
		issue.setIssueDetails(issueDto.getIssueDetails());
		issue.setIssueStatus("Open");
		issue.setAdminComments(issueDto.getAdminComments());
		issue.setCreatedBy(issueDto.getCreatedBy());
		issue.setCreatedDateTime(LocalDateTime.now());
		issue.setUpdatedBy(issueDto.getCreatedBy());
		issue.setUpdatedDateTime(LocalDateTime.now());
		issueRepository.save(issue);

		resultDto.setStatus(HireProUsConstants.RETURN_STATUS_OK);
		return resultDto;
	}

	@Override
	public List<IssueDto> getAllIssues() throws Exception {

		List<IssueDto> issueDtoList = new ArrayList<IssueDto>();

		List<Issue> issueList = issueRepository.findAll(HireProUsUtil.orderByUpdatedDateTimeDesc());
		for (Issue issue : issueList) {
			issueDtoList.add(this.getIssueDto(issue));
		}

		return issueDtoList;
	}
	
	private IssueDto getIssueDto(final Issue issue) throws Exception {

		IssueDto issueDto = new IssueDto();

		issueDto.setIssueId(issue.getIssueId());
		issueDto.setIssueType(issue.getIssueType());
		issueDto.setIssueDetails(issue.getIssueDetails());
		issueDto.setAdminComments(issue.getAdminComments());
		issueDto.setCreatedBy(issue.getCreatedBy());
		issueDto.setCreatedByName(userService.getUserNameById(issue.getCreatedBy()));
		issueDto.setCreatedDateTime(HireProUsUtil.getGMTDateTime(issue.getCreatedDateTime()));
		issueDto.setUpdatedBy(issue.getUpdatedBy());
		issueDto.setUpdatedByName(userService.getUserNameById(issue.getUpdatedBy()));
		issueDto.setUpdatedDateTime(HireProUsUtil.getGMTDateTime(issue.getUpdatedDateTime()));

		return issueDto;
	}

	@Override
	public IssueDto editIssue(final IssueDto issueDto, String lang) throws Exception {

		IssueDto resultDto = new IssueDto();

		Issue issue = issueRepository.findById(issueDto.getIssueId());

		if (issue == null) {
			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(commonService.getMessage("invalid", new String[] { "Issue Id" }, lang));

			logger.info(resultDto.getMessage());
			return resultDto;
		}
		
		issue.setIssueStatus(issueDto.getIssueStatus());
		issue.setAdminComments(issueDto.getAdminComments());
		issue.setUpdatedBy(issueDto.getUpdatedBy());
		issue.setUpdatedDateTime(LocalDateTime.now());

		issueRepository.save(issue);
		

		resultDto.setStatus(HireProUsConstants.RETURN_STATUS_OK);
		return resultDto;
	}

	@Override
	public IssueDto getIssueById(final String issueId) throws Exception {
		
		Issue issue = issueRepository.findById(Long.parseLong(issueId));

		return issue != null ? this.getIssueDto(issue) : new IssueDto();
	}
	
	@Override
	public List<IssueDto> searchIssue(final SearchIssueDto searchIssueDto, final String lang)
			throws Exception {

		final List<Criteria> criteriaList = new ArrayList<>();

		
		if (searchIssueDto.getIssueType() != null && !searchIssueDto.getIssueType().isEmpty()) {
			criteriaList.add(Criteria.where("issueType").is(searchIssueDto.getIssueType()));
		}
		if (searchIssueDto.getIssueStatus() != null && !searchIssueDto.getIssueStatus().isEmpty()) {
			criteriaList.add(Criteria.where("issueStatus").is(searchIssueDto.getIssueStatus()));
		}
		if (searchIssueDto.getCreatedBy() != null && !searchIssueDto.getCreatedBy().equals(0L)) {
			criteriaList.add(Criteria.where("createdBy").is(searchIssueDto.getCreatedBy()));
		}

		List<Issue> issueList = new ArrayList<Issue>();
		if (!criteriaList.isEmpty()) {
			Query searchQuery = new Query();
			searchQuery
					.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
			issueList = mongoTemplate.find(searchQuery, Issue.class);
		}

		List<IssueDto> issueDtoList = new ArrayList<IssueDto>();
		for (Issue issue : issueList) {
			issueDtoList.add(this.getIssueDto(issue));
		}

		return issueDtoList;
	}

	
}
