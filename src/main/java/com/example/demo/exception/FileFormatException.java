package com.example.demo.exception;

import com.example.demo.enums.ErrorType;

public class FileFormatException extends RuntimeException {

    private final ErrorType errorType;

    public ErrorType getErrorType() {
        return errorType;
    }

    public FileFormatException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
