package com.allstate_assessment.Advice;

import com.allstate_assessment.Exception.PersonExistsException;
import com.allstate_assessment.Exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler {

    /**
     * @param exception
     * @return Map
     * This method handles the error messages of the paramerts with which the constraints are not met
     * eg:  phone number is mandatory and it should in between 11 and 13 numbers etc.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }


    /**
     * @param exception
     * @return Map
     * handleInvalidArgument this method is used for handling the error message of duplicate last name
     * This method send backs the response with message to the ui
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersonExistsException.class)
    public Map<String, HttpStatus> handleInvalidArgument(PersonExistsException exception) {
        Map<String, HttpStatus> errorMap = new HashMap<>();
        errorMap.put(exception.getMessage(), exception.getHttpStatus());
        return errorMap;
    }

    /**
     * @param exception
     * @return Map
     * This method handles the error message of the PersonNotFoundException
     * When the user request for the person record which is not available in the db then this exception will raise
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersonNotFoundException.class)
    public Map<String, HttpStatus> personNotFound(PersonNotFoundException exception) {
        Map<String, HttpStatus> errorMap = new HashMap<>();
        errorMap.put(exception.getMessage(), exception.getHttpStatus());
        return errorMap;
    }


}
