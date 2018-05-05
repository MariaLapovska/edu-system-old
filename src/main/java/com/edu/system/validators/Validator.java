package com.edu.system.validators;

import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;

public interface Validator {
    ValidatorResult validate(String body, Test test) throws ValidatorException;
}
