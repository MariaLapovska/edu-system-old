package com.edu.system.validators.vo;

import lombok.Data;

@Data
public class ValidatorResult {
    private String message;
    private Boolean success;
    private int attempts;

    public ValidatorResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
        this.attempts = 0;
    }
}
