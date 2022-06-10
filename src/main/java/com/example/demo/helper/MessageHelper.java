package com.example.demo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    public String getMessageSource(String message) {
        try {
            return messageSource.getMessage(message, new Object[]{}, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            return message;
        }
    }
}
