/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Entity class for the MongoDB collection - roles.
 * 
 * @author Sihab.
 *
 */

public class IssueDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long issueId;
	private String issueType;
	private String issueDetails;
	private String adminComments;
	private Long createdBy;
	private String createdByName;
	private LocalDateTime createdDateTime;
	private Long repliedBy;
	private String repliedByName;
	private LocalDateTime repliedDateTime;
	private String issueStatus;
	private Long updatedBy;
	private String updatedByName;
	
	private int status;
	private String message;
	
	/**
	 * @return the issueId
	 */
	public Long getIssueId() {
		return issueId;
	}
	/**
	 * @param issueId the issueId to set
	 */
	public void setIssueId(Long issueId) {
		this.issueId = issueId;
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
	 * @return the createdByName
	 */
	public String getCreatedByName() {
		return createdByName;
	}
	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
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
	 * @return the repliedByName
	 */
	public String getRepliedByName() {
		return repliedByName;
	}
	/**
	 * @param repliedByName the repliedByName to set
	 */
	public void setRepliedByName(String repliedByName) {
		this.repliedByName = repliedByName;
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
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedByName
	 */
	public String getUpdatedByName() {
		return updatedByName;
	}
	/**
	 * @param updatedByName the updatedByName to set
	 */
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
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
	private LocalDateTime updatedDateTime;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
