package com.lei.tang.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanglei
 * @date 18/9/26
 */
@ControllerAdvice
public class ExceptionHandler {

    public static final String ERROR_URL = "/thymeleaf/error";

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e){
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURI());
        modelAndView.setViewName(ERROR_URL);
        return modelAndView;
    }
}
