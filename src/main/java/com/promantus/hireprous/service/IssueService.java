/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.service;

import java.util.List;


import com.promantus.hireprous.dto.IssueDto;
import com.promantus.hireprous.dto.SearchIssueDto;


/**
 * @author Sangeetha Srithar.
 *
 */

public interface IssueService {

	IssueDto addIssue(final IssueDto issueDto, String lang) throws Exception;

	List<IssueDto> getAllIssues() throws Exception;

	IssueDto editIssue(IssueDto issueDto, String lang) throws Exception;

	IssueDto getIssueById(String issueId) throws Exception;
	
	List<IssueDto> searchIssue(SearchIssueDto searchIssueDto, String lang) throws Exception;
	

}
