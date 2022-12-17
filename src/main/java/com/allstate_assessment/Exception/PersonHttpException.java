package com.allstate_assessment.Exception;

import org.springframework.http.HttpStatus;

public class PersonHttpException extends RuntimeException {

    private final String message;

    private final HttpStatus httpStatus;

    public PersonHttpException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;

    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}