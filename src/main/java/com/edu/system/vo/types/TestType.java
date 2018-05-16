package com.edu.system.vo.types;

import com.edu.system.validators.code.CodeValidator;
import com.edu.system.validators.open.OpenValidator;
import com.edu.system.validators.test.TestValidator;
import com.edu.system.validators.Validator;

public enum TestType {
    CODE {
        @Override
        public Validator getValidator() {
            return new CodeValidator();
        }
    }, TEST {
        @Override
        public Validator getValidator() {
            return new TestValidator();
        }
    }, OPEN {
        @Override
        public Validator getValidator() {
            return new OpenValidator();
        }
    };

    public abstract Validator getValidator();
}
