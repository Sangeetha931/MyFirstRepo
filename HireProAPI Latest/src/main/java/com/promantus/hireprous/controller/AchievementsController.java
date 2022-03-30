package com.promantus.hireprous.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promantus.hireprous.HireProUsConstants;
import com.promantus.hireprous.dto.AchievementsDto;
import com.promantus.hireprous.dto.BusinessUnitDto;
import com.promantus.hireprous.service.AchievementService;
import com.promantus.hireprous.util.HireProUsUtil;

@RestController
@RequestMapping
public class AchievementsController extends CommonController{

	private static final Logger logger = LoggerFactory.getLogger(AchievementsController.class);
	
	@Autowired
	private AchievementService achievementService;
	
	@PostMapping("/addAchievements")
	public AchievementsDto addAchievements (@RequestBody AchievementsDto achievementsDto, @RequestHeader(name = "lang", required = false) String lang) {
		
		AchievementsDto resultDto = new AchievementsDto();
		try {

			// Mandatory check.
			StringBuilder errorParam = new StringBuilder();
			// Achievements.
			if (achievementsDto.getAchievements() == null || achievementsDto.getAchievements().isEmpty()) {
				errorParam.append("Achievements is needed");
			}

			if (errorParam.length() > 0) {
				resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
				resultDto.setMessage(
						super.getMessage("mandatory.input.param", new String[] { errorParam.toString() }, lang));

				logger.info(resultDto.getMessage());
				return resultDto;
			}
			resultDto = achievementService.addAchievements(achievementsDto, lang);

		} catch (final Exception e) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(e.getMessage());

			logger.error(HireProUsUtil.getErrorMessage(e));
			return resultDto;
		}
		return resultDto;
		
}
	/**
	 * @return
	 */
	@GetMapping("/getAllAchievements")
	public List<AchievementsDto> getAllAchievements(@RequestHeader(name = "lang", required = false) String lang) {

		List<AchievementsDto> achievementsDtoList = new ArrayList<AchievementsDto>();
		try {
			achievementsDtoList = achievementService.getAllAchievements();
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return achievementsDtoList;
	}

	@PostMapping("/updateAchievements")
	public AchievementsDto updateAchievements (@RequestBody AchievementsDto achievementsDto, @RequestHeader(name = "lang", required = false) String lang) {
		
		AchievementsDto resultDto = new AchievementsDto();
		try {

			// Mandatory check.
			StringBuilder errorParam = new StringBuilder();
			// Achievements.
			if (achievementsDto.getAchievements() == null || achievementsDto.getAchievements().isEmpty()) {
				errorParam.append("Achievements is needed");
			}

			if (errorParam.length() > 0) {
				resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
				resultDto.setMessage(
						super.getMessage("mandatory.input.param", new String[] { errorParam.toString() }, lang));

				logger.info(resultDto.getMessage());
				return resultDto;
			}
			if (achievementsDto.getReviewStatus().equals("closed")) {
				resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
				resultDto.setMessage("Cannot edit the achievements, since it is reviewed");

				logger.info(resultDto.getMessage());
				return resultDto;
			}
			resultDto = achievementService.updateAchievements(achievementsDto, lang);

		} catch (final Exception e) {

			resultDto.setStatus(HireProUsConstants.RETURN_STATUS_ERROR);
			resultDto.setMessage(e.getMessage());

			logger.error(HireProUsUtil.getErrorMessage(e));
			return resultDto;
		}
		return resultDto;
		
}
	
	/**
	 * @param achievementId
	 * @return
	 */
	@GetMapping("/getAchievementsById/{achievementId}")
	public AchievementsDto getAchievementsById(@PathVariable String achievementId,
			@RequestHeader(name = "lang", required = false) String lang) {

		AchievementsDto achievementsDto = new AchievementsDto();
		try {
			achievementsDto = achievementService.getAchievementsById(achievementId);
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return achievementsDto;
	}
	
	/**
	 * @param achievementId
	 * @return
	 */
	@DeleteMapping("/deleteAcheivementsById/{achievementId}")
	public AchievementsDto deleteAcheivementsById(@PathVariable String achievementId,
			@RequestHeader(name = "lang", required = false) String lang) {

		try {
			return achievementService.deleteAchievementsById(achievementId, lang);
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return new AchievementsDto();
	}
	
	@GetMapping("/getAchievementsByResourceName/{resourceName}")
	public List<AchievementsDto> getAchievementsByResourceName(@PathVariable String resourceName,
			@RequestHeader(name = "lang", required = false) String lang) {

		List<AchievementsDto> achievementsDtoList = new ArrayList<AchievementsDto>();
		
		try {
			achievementsDtoList = achievementService.getAchievementsByResourceName(resourceName);
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return achievementsDtoList;
	}

	@PostMapping("/searchAchievements")
	public List<AchievementsDto> searchAchievementsDto(@RequestBody AchievementsDto achievementsDto,
			@RequestHeader(name = "lang", required = false) String lang) {

		try {
			return achievementService.searchAchievements(achievementsDto);
		} catch (final Exception e) {
			logger.error(HireProUsUtil.getErrorMessage(e));
		}

		return new ArrayList<AchievementsDto>();
	}
}
