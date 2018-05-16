package com.edu.system.validators.open;

import org.junit.Assert;

import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.vo.Test;

public class OpenValidatorTest {

    Validator validator = new OpenValidator();

    @org.junit.Test
    public void validate() throws ValidatorException {
        String condition = "Мама, дед, тест";
        String ans = "Дед и мама играли в тесты";
        Test test = new Test();
        test.setCondition(condition);
        Assert.assertTrue(validator.validate(ans, test).getSuccess());
    }

    @org.junit.Test
    public void validateInvalid() throws ValidatorException {
        String condtition = "Мама, дед, тест";
        String ans = "Дед и мама играли в нарды";
        Test test = new Test();
        test.setCondition(condtition);
        Assert.assertFalse(validator.validate(ans, test).getSuccess());
    }

    @org.junit.Test
    public void validateInvalid_v2() throws ValidatorException {
        String condtition = "Быабка";
        String ans = "Дед и мама играли в нарды";
        Test test = new Test();
        test.setCondition(condtition);
        Assert.assertFalse(validator.validate(ans, test).getSuccess());
    }


}
