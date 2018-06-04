package com.edu.system.service;

import java.util.List;

import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.types.TestType;

public interface TestService {

    ValidatorResult validate(Long testId, String payload, User user) throws ServiceException;
    Test create(String name, String body, String condition, TestType testType, Long articleId) throws ServiceException;
    List<Test> getByArticle(Long articleId) throws ServiceException;
    Test get(Long id) throws ServiceException;
    void delete(Long id) throws ServiceException;
    void delete(Test test) throws ServiceException;
    void update(Test test);
}
