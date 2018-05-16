package com.edu.system.validators.open;

import java.util.Arrays;

import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;

public class OpenValidator implements Validator {

    @Override
    public ValidatorResult validate(String body, Test test) throws ValidatorException {
        boolean result = Arrays.stream(test.getCondition().split(",")).map(String::trim).allMatch(condition -> body.toLowerCase().contains(condition.toLowerCase()));
        return new ValidatorResult(null, result);
    }
}
