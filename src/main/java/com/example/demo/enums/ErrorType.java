package com.example.demo.enums;

public enum ErrorType {
    DATA_NOT_FOUND("404", "service.exception.data-not-found"),
    INVALID_FORMAT("422", "service.exception.invalid-format");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
