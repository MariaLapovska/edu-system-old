package com.edu.system.validators;

import lombok.Getter;

@Getter
public class ValidatorException extends Exception {
    private String errorMessage;
    private Boolean disableAttempt;

    public ValidatorException(String errorMessage, Boolean disableAttempt) {
        this.errorMessage = errorMessage;
        this.disableAttempt = disableAttempt;
    }

}
