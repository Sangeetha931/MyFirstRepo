package com.promantus.hireprous.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promantus.hireprous.entity.Achievements;

public interface AchievementsRepository extends MongoRepository<Achievements, String> {


	List<Achievements> findAllByEmployeeId(String empId);

	Achievements findById(long id);

	void deleteById(long parseLong);

	List<Achievements> findByEmployeeName(String employeeName);

}
