package com.orient.unitech.controller;

import com.orient.unitech.model.exception.ErrorResponse;
import com.orient.unitech.model.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(RestException e){

        return ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }
}
