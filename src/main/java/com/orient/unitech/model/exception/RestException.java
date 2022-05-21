package com.orient.unitech.model.exception;

public class RestException extends RuntimeException{
    private int code;
    private String message;

    public RestException(ErrorCodeEnum errorEnum) {
        this.code = errorEnum.getValue();
        this.message = errorEnum.name().toLowerCase();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
