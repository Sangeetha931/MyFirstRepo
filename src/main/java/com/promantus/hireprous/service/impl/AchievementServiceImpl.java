package com.promantus.hireprous.service.impl;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.promantus.hireprous.HireProUsConstants;
import com.promantus.hireprous.dto.AchievementsDto;
import com.promantus.hireprous.dto.BusinessUnitDto;
import com.promantus.hireprous.entity.Achievements;
import com.promantus.hireprous.entity.BusinessUnit;
import com.promantus.hireprous.repository.AchievementsRepository;
import com.promantus.hireprous.service.AchievementService;
import com.promantus.hireprous.service.CommonService;
import com.promantus.hireprous.util.CacheUtil;
import com.promantus.hireprous.util.HireProUsUtil;

@Service
public class AchievementServiceImpl implements AchievementService {

	private static final Logger logger = LoggerFactory.getLogger(AchievementServiceImpl.class); 
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AchievementsRepository achievementsRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public AchievementsDto addAchievements(AchievementsDto achievementsDto, String lang) throws Exception {

		AchievementsDto resultDto = new AchievementsDto();
		
		if (this.checkAchievements(achievementsDto.getCreatedDateTime().getYear(), achievementsDto.getEmployeeId())) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto
					.setMessage(commonService.getMessage("already.exists", new String[] { "Achievements for this year is already exists" }, lang));

			logger.info(resultDto.getMessage());
			return resultDto;
		}

		Achievements achievements = new Achievements();
		achievements.setId(commonService.nextSequenceNumber());
		achievements.setBusinessUnitId(achievementsDto.getBusinessUnitId());
		achievements.setBusinessUnitName(achievementsDto.getBusinessUnitName());
		achievements.setEmployeeId(achievementsDto.getEmployeeId());
		achievements.setEmployeeName(achievementsDto.getEmployeeName());
		achievements.setAchievements(achievementsDto.getAchievements());
		achievements.setReviewStatus("open");
		achievements.setCreatedBy(achievementsDto.getCreatedBy());
		achievements.setUpdatedBy(achievementsDto.getUpdatedBy());

		achievements.setCreatedDateTime(achievementsDto.getCreatedDateTime());
		achievements.setUpdatedDateTime(achievementsDto.getCreatedDateTime());
		achievements.setYear(achievementsDto.getCreatedDateTime().getYear());

		achievementsRepository.save(achievements);


		resultDto.setStatus(HireProUsConstants.RETURN_STATUS_OK);
		return resultDto;
	}

	private boolean checkAchievements(int createdYear, String empId) {
		
		List<Achievements> achievementsList = achievementsRepository.findAllByEmployeeId(empId);
		
		for(Achievements achievements : achievementsList) {
			if(achievements.getCreatedDateTime().getYear() == createdYear) {
				return true;
			};
		}
		return false;
	}



	@Override
	public List<AchievementsDto> getAllAchievements() throws Exception {

		List<AchievementsDto> achievementsDtoList = new ArrayList<AchievementsDto>();

		List<Achievements> achievementsList = achievementsRepository
				.findAll(HireProUsUtil.orderByUpdatedDateTimeDesc());
		for (Achievements achievements : achievementsList) {
			achievementsDtoList.add(this.getAchievementsDto(achievements));
		}

		return achievementsDtoList;
	}
	
	@Override
	public AchievementsDto updateAchievements(final AchievementsDto achievementsDto, String lang) throws Exception {

		AchievementsDto resultDto = new AchievementsDto();

		Achievements achievements = achievementsRepository.findById(achievementsDto.getId());

		if (achievements == null) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(commonService.getMessage("invalid", new String[] { "Achievement Id" }, lang));

			logger.info(resultDto.getMessage());
			return resultDto;
		}

		achievements.setAchievements(achievementsDto.getAchievements());

		achievements.setUpdatedBy(achievementsDto.getUpdatedBy());
		achievements.setUpdatedDateTime(LocalDateTime.now());

		achievementsRepository.save(achievements);

		resultDto.setStatus(HireProUsConstants.RETURN_STATUS_OK);
		return resultDto;
	}

	@Override
	public AchievementsDto getAchievementsById(final String achievementId) throws Exception {

		Achievements achievements = achievementsRepository.findById(Long.parseLong(achievementId));

		return achievements != null ? this.getAchievementsDto(achievements) : new AchievementsDto();
	}

	@Override
	public AchievementsDto deleteAchievementsById(final String achievementsId, final String lang) throws Exception {

		AchievementsDto resultDto = new AchievementsDto();

		Achievements achievements = achievementsRepository.findById(Long.parseLong(achievementsId));

		if (achievements == null) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(commonService.getMessage("invalid", new String[] { "Achievements Id" }, lang));

			logger.info(resultDto.getMessage());
			return resultDto;
		}
		else if (achievements.getReviewStatus().contentEquals("closed")) {
			
			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage("cannot delete, status is reviewed");

			logger.info(resultDto.getMessage());
			return resultDto;
		}

		else {
			achievementsRepository.deleteById(Long.parseLong(achievementsId));
			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_OK);

			CacheUtil.getBusMap().remove(achievements.getId());
		}

		return resultDto;
	}

	@Override
	public List<AchievementsDto> getAchievementsByResourceName(final String resourceName) throws Exception {
		
		List<AchievementsDto> achievementsDtoList = new ArrayList<AchievementsDto>();
		List<Achievements> achievementsList = achievementsRepository.findByEmployeeName(resourceName);
		
		for (Achievements achievements : achievementsList) {
			achievementsDtoList.add(this.getAchievementsDto(achievements));
		}
		return achievementsDtoList;
	}
	
	private AchievementsDto getAchievementsDto(final Achievements achievements) throws Exception {

		AchievementsDto achievementsDto = new AchievementsDto();

		achievementsDto.setId(achievements.getId());
		achievementsDto.setEmployeeId(achievements.getEmployeeId());
		achievementsDto.setEmployeeName(achievements.getEmployeeName());
		achievementsDto.setAchievements(achievements.getAchievements());
		achievementsDto.setReviewStatus(achievements.getReviewStatus());
		achievementsDto.setRating(achievements.getRating());
		
		achievementsDto.setBusinessUnitName(achievements.getBusinessUnitName());
		achievementsDto.setBusinessUnitId(achievements.getBusinessUnitId());

		achievementsDto.setCreatedBy(achievements.getCreatedBy());
		achievementsDto.setCreatedByName(CacheUtil.getUsersMap().get(achievements.getCreatedBy()));
		achievementsDto.setCreatedDateTime(HireProUsUtil.getGMTDateTime(achievements.getCreatedDateTime()));

		achievementsDto.setUpdatedBy(achievements.getUpdatedBy());
		achievementsDto.setUpdatedByName(CacheUtil.getUsersMap().get(achievements.getUpdatedBy()));
		achievementsDto.setUpdatedDateTime(HireProUsUtil.getGMTDateTime(achievements.getUpdatedDateTime()));
		achievementsDto.setYear(achievements.getYear());

		return achievementsDto;
	}

	@Override
	public List<AchievementsDto> searchAchievements(AchievementsDto achievementsDto) throws Exception {
	
		List<AchievementsDto> achievementsDtoList = new ArrayList<AchievementsDto>();
		List<Achievements> achievementsList = new ArrayList<Achievements>();
		
		final List<Criteria> criteriaList = new ArrayList<>();
		
		if (achievementsDto.getBusinessUnitId() != null) {
			criteriaList.add(Criteria.where("businessUnitId").is(achievementsDto.getBusinessUnitId()));
		}
		if (achievementsDto.getBusinessUnitName() != null && !achievementsDto.getBusinessUnitName().isEmpty()) {
			criteriaList.add(Criteria.where("businessUnitName").regex("(?i).*" + achievementsDto.getBusinessUnitName() + ".*"));
		}
		if (achievementsDto.getEmployeeName() != null && !achievementsDto.getEmployeeName().isEmpty()) {
			criteriaList.add(Criteria.where("employeeName").regex("(?i).*" + achievementsDto.getEmployeeName()));
		}
		if (achievementsDto.getReviewStatus() != null && !achievementsDto.getReviewStatus().isEmpty()) {
			criteriaList.add(Criteria.where("reviewStatus").is(achievementsDto.getReviewStatus()));
		}
		if (achievementsDto.getRating() != null && !achievementsDto.getRating().isEmpty()) {
			criteriaList.add(Criteria.where("rating").is(achievementsDto.getRating()));
		}
		if (achievementsDto.getYear() != 0) {
			criteriaList.add(Criteria.where("year").is(achievementsDto.getYear()));
//			List<Achievements > achievementslst = achievementsRepository.findAll();
//			for(Achievements achievements : achievementslst) {
//				if((achievements.getCreatedDateTime().getYear() == achievementsDto.getYear())) {
//					achievementsList.add(achievements);
//				}
//			}
		}
		
		if (!criteriaList.isEmpty()) {
			Query searchQuery = new Query();
			searchQuery
					.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
			achievementsList = mongoTemplate.find(searchQuery, Achievements.class);
		}

		for (Achievements achievements : achievementsList) {
			achievementsDtoList.add(this.getAchievementsDto(achievements));
		}

		Comparator<AchievementsDto> compareByUpdatedDateTime = Comparator
				.comparing(AchievementsDto::getUpdatedDateTime);
		achievementsDtoList.stream().sorted(compareByUpdatedDateTime).collect(Collectors.toList());

		return 	achievementsDtoList;
	}
}
