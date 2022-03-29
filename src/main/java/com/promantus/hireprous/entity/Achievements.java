package com.promantus.hireprous.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "achievements")
public class Achievements {

	@Id
	private long id;
	private String employeeId;
	private String employeeName;
	private Long businessUnitId;
	private String businessUnitName;
	private String achievements;
	private String reviewStatus;
	private String rating;
	private long createdBy;
	private LocalDateTime createdDateTime;
	private long updatedBy;
	private LocalDateTime updatedDateTime;
	private int year;
	
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the emp_id
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @return the businessUnitId
	 */
	public Long getBusinessUnitId() {
		return businessUnitId;
	}
	/**
	 * @return the businessUnitName
	 */
	public String getBusinessUnitName() {
		return businessUnitName;
	}
	/**
	 * @return the achievements
	 */
	public String getAchievements() {
		return achievements;
	}
	/**
	 * @return the status
	 */
	public String getReviewStatus() {
		return reviewStatus;
	}
	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * @return the createdDateTime
	 */
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	/**
	 * @return the updatedBy
	 */
	public long getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @return the updatedDateTime
	 */
	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param emp_id the emp_id to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @param businessUnitId the businessUnitId to set
	 */
	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}
	/**
	 * @param businessUnitName the businessUnitName to set
	 */
	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}
	/**
	 * @param achievements the achievements to set
	 */
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	/**
	 * @param status the status to set
	 */
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
}
