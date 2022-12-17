package com.allstate_assessment.Exception;

import org.springframework.http.HttpStatus;

public class PersonExistsException extends PersonHttpException{

    public PersonExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
