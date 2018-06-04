package com.edu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.AbstractCadrRepository;
import com.edu.system.repository.TestRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.service.UserAttemptService;
import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Article;
import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;
import com.edu.system.vo.types.TestType;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final ArticleService articleService;
    private final AbstractCadrRepository abstractCadrRepository;
    private final UserAttemptService userAttemptService;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, ArticleService articleService, AbstractCadrRepository abstractCadrRepository, UserAttemptService userAttemptService) {
        this.testRepository = testRepository;
        this.articleService = articleService;
        this.abstractCadrRepository = abstractCadrRepository;
        this.userAttemptService = userAttemptService;
    }

    @Override
    public ValidatorResult validate(Long testId, String payload, User user) throws ServiceException {
        Test test = testRepository.findById(testId).orElseThrow(() -> new ServiceException("Test not found"));
        Validator validator = test.getTestType().getValidator();
        UserAttempt userAttempt = userAttemptService.getUserAttempt(user, test);
        try {
            ValidatorResult validate = validator.validate(payload, test);
            userAttempt.setCount(userAttempt.getCount() + 1);
            userAttempt.setResult(validate.getSuccess());
            userAttempt = userAttemptService.update(userAttempt);
            validate.setAttempts(userAttempt.getCount());
            return validate;
        } catch (ValidatorException e) {
            if (e.getDisableAttempt()) {
                throw new ServiceException(e);
            } else {
                userAttempt.setCount(userAttempt.getCount() + 1);
                userAttemptService.update(userAttempt);
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
        Article article = articleService.get(articleId);
        test.setArticle(article);
        test = testRepository.save(test);
        return test;
    }

    @Override
    public List<Test> getByArticle(Long articleId) throws ServiceException {
        return testRepository.findByArticle(articleService.get(articleId));
    }

    @Override
    public Test get(Long id) throws ServiceException {
        return testRepository.findById(id).orElseThrow(() -> new ServiceException("Cant find test by id: " + id));
    }

    @Override
    public void delete(Long id) throws ServiceException {
        delete(get(id));
    }

    @Override
    public void delete(Test test) throws ServiceException {
        testRepository.delete(test);
    }

    @Override
    public void update(Test test) {
        testRepository.save(test);
    }
}
