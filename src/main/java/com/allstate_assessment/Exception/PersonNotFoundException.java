package com.allstate_assessment.Exception;

import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends PersonHttpException {

    public PersonNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
