package com.edu.system.validators.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidatorResult {
    private String message;
    private Boolean success;
}
