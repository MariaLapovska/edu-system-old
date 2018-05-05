package com.edu.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.TestRepository;
import com.edu.system.rest.vo.TestContent;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.code.CodeValidator;
import com.edu.system.validators.test.TestValidator;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;
import com.edu.system.vo.types.TestType;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestContent create(TestContent testContent) throws ServiceException {
        return TestContent.from(testRepository.save(prepareTest(testContent)));
    }

    @Override
    public ValidatorResult validate(TestType testType, Long testId, String payload) throws ServiceException {
        Validator validator;
        switch (testType) {
            case CODE:
                validator = new CodeValidator();
                break;
            case TEST:
                validator = new TestValidator();
                break;
            default:
                throw new ServiceException("Validator not found");
        }
        Test test = testRepository.findById(testId).orElseThrow(() -> new ServiceException("Test not found"));
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
    public TestContent findNext(Long testId) throws ServiceException {
        return TestContent.from(testRepository.findNextTest(testId).orElseThrow(() -> new ServiceException("Test not found id: " + testId)));
    }

    private Test prepareTest(TestContent content) throws ServiceException {
        Test test = new Test();
        test.setName(content.getName());
        test.setBody(content.getBody());
        test.setCondition(content.getCondition());
        test.setFileToWrite(content.getFileToWrite());
        test.setVariants(content.getVariants());
        test.setTestType(content.getTestType());
        test.setCodeType(content.getCodeType());
        if (content.getTest() != null) {
            test.setTest(testRepository.findById(content.getTest()).orElseThrow(() -> new ServiceException("Invalid test id: " + content.getTest())));
        }
        return test;
    }
}
