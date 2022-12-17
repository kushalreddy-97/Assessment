package com.allstate_assessment.Validation;

import com.allstate_assessment.Exception.InputFieldValidationException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class ValidatorUtil {
    public static final Pattern PHONE_NUMBER_PATTERN = Pattern
            .compile("^\\d+$");

    public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]*$");

    private ValidatorUtil() {
    }

    public static <T> void handleValidation(T t, Validator validator) {
        DataBinder dataBinder = new DataBinder(t);
        dataBinder.setValidator(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors()) {
            throw new InputFieldValidationException("Validation Error", dataBinder.getBindingResult().getFieldErrors());
        }
    }

    public static boolean isValidPhoneNumber(String number) {
        return PHONE_NUMBER_PATTERN.matcher(number).matches();
    }


    public static boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }


}
