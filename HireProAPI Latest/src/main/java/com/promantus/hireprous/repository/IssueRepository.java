/**********************************************************************************************
 * Copyright 2021 Promantus Private Limited.
 * All rights reserved.
 **********************************************************************************************/
package com.promantus.hireprous.repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.promantus.hireprous.entity.Issue;


/**
 * @author Sangeetha Srithar.
 *
 */
public interface IssueRepository extends MongoRepository<Issue, String> {

	Issue findById(Long issueId);

}
