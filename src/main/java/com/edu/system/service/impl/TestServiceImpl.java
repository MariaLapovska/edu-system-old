package com.edu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.TestRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;
import com.edu.system.vo.types.TestType;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final ArticleService articleService;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, ArticleService articleService) {
        this.testRepository = testRepository;
        this.articleService = articleService;
    }

    @Override
    public ValidatorResult validate(Long testId, String payload) throws ServiceException {
        Test test = testRepository.findById(testId).orElseThrow(() -> new ServiceException("Test not found"));
        Validator validator = test.getTestType().getValidator();
        try {
            return validator.validate(payload, test);
        } catch (ValidatorException e) {
            if (e.getDisableAttempt()) {
                throw new ServiceException(e);
            } else {
                return new ValidatorResult(e.getErrorMessage(), false);
            }
        }
    }

    @Override
    public Test create(String name, String body, String condition, TestType testType, Long articleId) throws ServiceException {
        Test test = new Test();
        test.setName(name);
        test.setBody(body);
        test.setCondition(condition);
        test.setTestType(testType);
        test.setArticle(articleService.get(articleId));
        return testRepository.save(test);
    }

    @Override
    public List<Test> getByArticle(Long articleId) throws ServiceException {
        return testRepository.findByArticle(articleService.get(articleId));
    }

    @Override
    public Test get(Long id) throws ServiceException {
        return testRepository.findById(id).orElseThrow(()-> new ServiceException("Cant find test by id: " + id));
    }

    @Override
    public void delete(Long id) throws ServiceException {
        delete(get(id));
    }

    @Override
    public void delete(Test test) throws ServiceException {
        testRepository.delete(test);
    }
}
