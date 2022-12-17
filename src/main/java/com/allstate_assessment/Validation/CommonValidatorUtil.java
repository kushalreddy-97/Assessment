package com.allstate_assessment.Validation;

import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor
public class CommonValidatorUtil {

    public static final String SPECIAL_CHARACTERS = "[" + "-/@#!*$%^&.'_+={}()" + "]+";
    private static final Pattern NUMERIC_INTEGER_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern ALPHANUMARIC_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]*$");
    private static final Pattern ALPHABETICAL_PATTERN = Pattern.compile("^[a-zA-Z ]*$");
    private static final Pattern IS_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]*$");

    public static boolean isAlphanumeric(String stringAndNumber) {
        boolean result = true;
        result = ALPHANUMARIC_PATTERN.matcher(String.valueOf(stringAndNumber)).matches();
        return result;

    }

    public static boolean isAlphabetical(String string) {
        boolean result = true;
        result = ALPHABETICAL_PATTERN.matcher(String.valueOf(string)).matches();
        return result;
    }

    public static boolean isNumeric(String number) {
        boolean result = true;
        result = NUMERIC_INTEGER_PATTERN.matcher(String.valueOf(number)).matches();
        return result;

    }

    public static boolean isPhoneNumber(String phoneNumber) {
        boolean result = false;
        result = NUMERIC_INTEGER_PATTERN.matcher(String.valueOf(phoneNumber)).matches();
        if (result && (phoneNumber.length() < 10 || phoneNumber.length() > 11)) {
            result = false;
        }
        return result;
    }

    public static boolean isName(String name) {
        boolean result = true;
        result = IS_NAME_PATTERN.matcher(String.valueOf(name)).matches();
        if (!result) {
            return result;
        } else {
            result = ("" + name.charAt(0)).matches("^[a-zA-Z]");
            return result;
        }
    }

}

