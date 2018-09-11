package com.java.exception;

public enum ExceptionEnum {
    UN_KNOWN(-1, "未知错误"), PRIMARY_SCHOOL(100, "小学生"), MIDDLE_SCHOOL(101, "中学生");

    private final Integer code;

    private final String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
