package com.orient.unitech.model.exception;

public enum ErrorCodeEnum {

    PIN_OR_PASSWORD_WRONG_OR_DEACTIVE(407),
    USER_NOT_FOUND(408),
    BALANCE_NOT_ENOUGH(409),
    No_value_present(410),
    PIN_ALREADY_EXISTS(411);

    private int value;

    ErrorCodeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
