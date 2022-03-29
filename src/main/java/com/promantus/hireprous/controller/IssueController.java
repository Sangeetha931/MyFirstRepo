/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promantus.hireprous.HireProUsConstants;
import com.promantus.hireprous.dto.IssueDto;
import com.promantus.hireprous.dto.SearchIssueDto;
import com.promantus.hireprous.service.IssueService;
import com.promantus.hireprous.util.HireProUsUtil;


/**
 * Controller class to handle issues related APIs.
 * @author Sangeetha Sridhar
 *
 */


@RestController
@RequestMapping("/api/v1")
public class IssueController extends CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	@Autowired
	private IssueService issueService;


	@PostMapping("/addIssue")
	public IssueDto addIssue(@RequestBody IssueDto issueDto, @RequestHeader(name = "lang", required = false) String lang) {

		IssueDto resultDto = new IssueDto();
		try {

			StringBuilder errorParam = new StringBuilder();
			// Issue Type
			if (issueDto.getIssueType() == null || issueDto.getIssueType().isEmpty()) {
				errorParam.append("Issue Type");
			}
			// Issue Details
			if (issueDto.getIssueDetails() == null || issueDto.getIssueDetails().isEmpty()) {
				errorParam.append(errorParam.length() > 0 ? ", Issue Details" : "Issue Details");
			}
			if (errorParam.length() > 0) {
				resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
				resultDto.setMessage(
						super.getMessage("mandatory.input.param", new String[] { errorParam.toString() }, lang));

				logger.info(resultDto.getMessage());
				return resultDto;
			}

			resultDto = issueService.addIssue(issueDto, lang);
		}
		catch (final Exception e) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(e.getMessage());

			logger.error(HireProUsUtil.getErrorMessage(e));
			return resultDto;
		}

		return resultDto;
	}
	
	@GetMapping("/getAllIssues")
	public List<IssueDto> getAllIssues(@RequestHeader(name = "lang", required = false) String lang) {

		List<IssueDto> issueDtoList = new ArrayList<IssueDto>();
		try {
			issueDtoList = issueService.getAllIssues();
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return issueDtoList;
	}

	
	@PutMapping("/EditIssue")
	public IssueDto EditIssue(@RequestBody IssueDto issueDto,
			@RequestHeader(name = "lang", required = false) String lang) {

		IssueDto resultDto = new IssueDto();
		try {

			// Mandatory check.
			StringBuilder errorParam = new StringBuilder();	
			// Issue Status.
			if (issueDto.getIssueStatus() == null || issueDto.getIssueStatus().isEmpty()) {
				errorParam.append("Issue Status");
			}
			//Admin Comments
			if (issueDto.getAdminComments() == null || issueDto.getAdminComments().isEmpty()) {
				errorParam.append(errorParam.length() > 0 ? ", Admin Comments" : "Admin Comments");
			}
			if (errorParam.length() > 0) {
				resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
				resultDto.setMessage(
						super.getMessage("mandatory.input.param", new String[] { errorParam.toString() }, lang));

				logger.info(resultDto.getMessage());
				return resultDto;
			}

			resultDto = issueService.editIssue(issueDto, lang);

		} catch (final Exception e) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(e.getMessage());

			logger.error(HireProUsUtil.getErrorMessage(e));
			return resultDto;
		}

		return resultDto;
	}

	@GetMapping("/getIssueById/{issueId}")
	public IssueDto getIssueById(@PathVariable String issueId,
			@RequestHeader(name = "lang", required = false) String lang) {

		IssueDto issueDto = new IssueDto();
		try {
			issueDto = issueService.getIssueById(issueId);
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return issueDto;
	}

	@PostMapping("/searchIssue")
	public List<IssueDto> searchIssue(@RequestBody SearchIssueDto searchIssueDto,
			@RequestHeader(name = "lang", required = false) String lang) {

		try {

			return issueService.searchIssue(searchIssueDto, lang);

		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return new ArrayList<IssueDto>();
	}
}
