package com.allstate_assessment.Exception;

import org.springframework.validation.FieldError;

import java.util.List;

public class InputFieldValidationException extends RuntimeException {
    private static final long serialVersionUID = 3940740629410252651L;
    final transient List<FieldError> fieldErrorList;

    public InputFieldValidationException(String message, List<FieldError> errorCodeList) {
        super(message);
        this.fieldErrorList = errorCodeList;
    }

    public List<FieldError> getFieldErrorList() {
        return fieldErrorList;
    }
}
