package com.edu.system.service;

import com.edu.system.rest.vo.TestContent;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.types.TestType;

public interface TestService {

    TestContent create(TestContent testContent) throws ServiceException;

    ValidatorResult validate(TestType testType, Long testId, String payload) throws ServiceException;

    TestContent findNext(Long testId) throws ServiceException;

}
