package com.edu.system.validators.test;

import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;

public class TestValidator implements Validator {
    @Override
    public ValidatorResult validate(String body, Test test) throws ValidatorException {
        return new ValidatorResult(body, test.getCondition().equals(body));
    }
}
