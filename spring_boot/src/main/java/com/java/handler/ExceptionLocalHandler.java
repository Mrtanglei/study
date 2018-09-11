package com.java.handler;

import com.java.entity.Result;
import com.java.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionLocalHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody//返回json格式
    public Result handleException(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return new Result(businessException.getCode(), businessException.getMessage());
        }
        return new Result(-1, e.getMessage());
    }
}
