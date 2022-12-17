package com.allstate_assessment.Util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum PersonErrorCode implements IErrorCode {

    PERSON_WITH_LAST_NAME_ALREADY_EXISTS(2001, "person.lastName already exists"),
    PERSON_WITH_PHONE_NUMBER_ALREADY_EXISTS(2002, "person.phoneNumber already exists"),
    PERSON_LAST_NAME_INVALID(2003,"person.lastName Invalid"),
    PERSON_PHONE_NUMBER_INVALID(2003,"person.phoneNumber Invalid"),
    PERSON_ADDRESS_ADDRESS_LINE1_INVALID(2004,"person.address.addressLine1"),
    PERSON_ADDRESS_POSTCODE_INVALID(2005,"person.address.postcode invalid"),
    PERSON_ADDRESS_COUNTRY_INVALID(2006,"person.address.country invalid"),
    PERSON_MOBILE_NUMBER_INVALID(2007,"person.mobileNumber Invalid"),
    PERSON_FIRST_NAME_INVALID(2008,"person.firstName Invalid"),
    DEFAULT(2000, "unknown error");
    private int code;
    private String message;

    private PersonErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PersonErrorCode getByCodeStr(String codeStr) {
        for (PersonErrorCode e : values()) {
            if (e.getCodeStr().equals(codeStr)) return e;
        }
        return DEFAULT;
    }


}
