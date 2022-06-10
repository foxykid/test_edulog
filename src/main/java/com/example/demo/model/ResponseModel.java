package com.example.demo.model;

import org.springframework.http.HttpStatus;

public class ResponseModel {

    private boolean result = true;

    private String code = "200";

    private String message = "Success";

    private Object data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseModel okInstance() {
        ResponseModel model = new ResponseModel();
        model.result = true;
        model.code = String.valueOf(HttpStatus.OK.value());
        model.message = HttpStatus.OK.getReasonPhrase();
        return model;
    }

    public static ResponseModel createdInstance() {
        ResponseModel model = new ResponseModel();
        model.result = true;
        model.code = String.valueOf(HttpStatus.CREATED.value());
        model.message = HttpStatus.CREATED.getReasonPhrase();
        return model;
    }

    public ResponseModel withData(Object data) {
        this.data = data;
        return this;
    }

}
