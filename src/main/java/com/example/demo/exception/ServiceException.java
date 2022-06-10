package com.example.demo.exception;

import com.example.demo.enums.ErrorType;

public class ServiceException extends RuntimeException{

    private final ErrorType errorType;

    public ErrorType getErrorType() {
        return errorType;
    }

    public ServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
