package com.example.demo.exception.handler;

import com.example.demo.controller.GPSController;
import com.example.demo.enums.ErrorType;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.FileFormatException;
import com.example.demo.exception.ServiceException;
import com.example.demo.helper.MessageHelper;
import com.example.demo.model.ResponseModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.Optional;

@ControllerAdvice
public class ServiceExceptionHandler {

    @Autowired
    private MessageHelper messageHelper;

    private static final Logger log = Logger.getLogger(GPSController.class);

    @ExceptionHandler(FileFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseModel fileFormatException(FileFormatException e) {
        return setResponseModel(e.getErrorType(), null, null);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseModel dataNotFoundException(DataNotFoundException e) {
        return setResponseModel(e.getErrorType(), null, null);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseModel serviceException(ServiceException e) {
        return setResponseModel(e.getErrorType(), null, null);
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseModel multipartException(MultipartException e) {
        return setResponseModel(null, ErrorType.INVALID_FORMAT.getCode(),messageHelper.getMessageSource(ErrorType.INVALID_FORMAT.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseModel exception(Exception e) {
        return setResponseModel(null, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    private ResponseModel setResponseModel(ErrorType errorType, String code, String message) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setResult(false);
        responseModel.setCode(Optional.ofNullable(code).isPresent() ? code : errorType.getCode());
        responseModel.setMessage(Optional.ofNullable(message).isPresent() ? message : messageHelper.getMessageSource(errorType.getMessage()));
        log.error("=========================" + (Optional.ofNullable(message).isPresent() ? message
                : messageHelper.getMessageSource(errorType.getMessage())) + "=========================");
        return responseModel;
    }
}
