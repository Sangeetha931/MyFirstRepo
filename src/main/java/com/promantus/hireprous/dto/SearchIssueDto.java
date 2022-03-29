/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.dto;

import java.io.Serializable;

/**
 * Entity class for the MongoDB collection - job_requests.
 * 
 * @author Sangeetha Srithar.
 *
 */

public class SearchIssueDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String issueType;
	private Long createdBy;
	private String issueStatus;
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the issueType
	 */
	public String getIssueType() {
		return issueType;
	}
	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}
	/**
	 * @return the issueStatus
	 */
	public String getIssueStatus() {
		return issueStatus;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param issueType the issueType to set
	 */
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @param issueStatus the issueStatus to set
	 */
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

}
