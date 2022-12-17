package com.allstate_assessment.Exception;

import com.allstate_assessment.Util.IErrorCode;

import java.io.Serial;
import java.util.List;

public class DuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2564698220544727247L;
    final transient List<IErrorCode> errorCodes;

    public DuplicateException(String message, List<IErrorCode> duplicateErrorCodes) {
        super(message);
        this.errorCodes = duplicateErrorCodes;
    }

    public List<IErrorCode> getErrorCodes() {
        return errorCodes;
    }
}
