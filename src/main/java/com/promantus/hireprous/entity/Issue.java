/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity class for the MongoDB collection - roles.
 * 
 * @author Sihab.
 *
 */
@Document(collection = "issue")
public class Issue {

	@Id
	private Long id;
	private String ticketNumber;
	private Integer nextCounter;
	private Integer runningNumber;
	private String issueType;
	private String issueDetails;
	private String adminComments;
	private Long createdBy;
	private LocalDateTime createdDateTime;
	private Long repliedBy;
	private LocalDateTime repliedDateTime;
	private String issueStatus;
	private Long updatedBy;
	private LocalDateTime updatedDateTime;
	
	
	
	/**
	 * @return the issueId
	 */
	public Long getIssueId() {
		return id;
	}
	/**
	 * @param issueId the issueId to set
	 */
	public void setIssueId(Long id) {
		this.id = id;
	}
	/**
	 * @return the issueType
	 */
	public String getIssueType() {
		return issueType;
	}
	/**
	 * @param issueType the issueType to set
	 */
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	/**
	 * @return the issueDetails
	 */
	public String getIssueDetails() {
		return issueDetails;
	}
	/**
	 * @param issueDetails the issueDetails to set
	 */
	public void setIssueDetails(String issueDetails) {
		this.issueDetails = issueDetails;
	}
	/**
	 * @return the adminComments
	 */
	public String getAdminComments() {
		return adminComments;
	}
	/**
	 * @param adminComments the adminComments to set
	 */
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}
	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDateTime
	 */
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	/**
	 * @return the repliedBy
	 */
	public Long getRepliedBy() {
		return repliedBy;
	}
	/**
	 * @param repliedBy the repliedBy to set
	 */
	public void setRepliedBy(Long repliedBy) {
		this.repliedBy = repliedBy;
	}
	/**
	 * @return the repliedDateTime
	 */
	public LocalDateTime getRepliedDateTime() {
		return repliedDateTime;
	}
	/**
	 * @param repliedDateTime the repliedDateTime to set
	 */
	public void setRepliedDateTime(LocalDateTime repliedDateTime) {
		this.repliedDateTime = repliedDateTime;
	}
	/**
	 * @return the status
	 */
	public String getIssueStatus() {
		return issueStatus;
	}
	/**
	 * @param status the status to set
	 */
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	/**
	 * @return the updatedBy
	 */
	public Long getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @return the runningNumber
	 */
	public Integer getRunningNumber() {
		return runningNumber;
	}
	/**
	 * @param runningNumber the runningNumber to set
	 */
	public void setRunningNumber(Integer runningNumber) {
		this.runningNumber = runningNumber;
	}
	/**
	 * @return the nextCounter
	 */
	public Integer getNextCounter() {
		return nextCounter;
	}
	/**
	 * @param nextCounter the nextCounter to set
	 */
	public void setNextCounter(Integer nextCounter) {
		this.nextCounter = nextCounter;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDateTime
	 */
	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}
	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	/**
	 * @return the ticketNumber
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}
	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	
	
}
