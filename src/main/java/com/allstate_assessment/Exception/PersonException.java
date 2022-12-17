package com.allstate_assessment.Exception;

public class PersonException extends RuntimeException {

    public PersonException() {
        super();
    }

    public PersonException(String message) {
        super(message);
    }

    public PersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
