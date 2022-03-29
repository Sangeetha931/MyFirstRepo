package com.promantus.hireprous.service;

import java.util.List;

import com.promantus.hireprous.dto.AchievementsDto;

public interface AchievementService {

	AchievementsDto addAchievements(final AchievementsDto achievementsDto, String lang) throws Exception;

	List<AchievementsDto> getAllAchievements() throws Exception;

	AchievementsDto updateAchievements(AchievementsDto achievementsDto, String lang) throws Exception;

	AchievementsDto getAchievementsById(String achievementId) throws Exception;

	AchievementsDto deleteAchievementsById(String achievementId, String lang) throws Exception;

	List<AchievementsDto> getAchievementsByResourceName(String resourceName) throws Exception;

	List<AchievementsDto> searchAchievements(AchievementsDto achievementsDto) throws Exception;

	
}
