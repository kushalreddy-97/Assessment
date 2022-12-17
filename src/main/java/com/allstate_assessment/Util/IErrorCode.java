package com.allstate_assessment.Util;

public interface IErrorCode {

    int getCode();

    String getMessage();

    default String getCodeStr() {
        return String.valueOf(getCode());
    }
}
