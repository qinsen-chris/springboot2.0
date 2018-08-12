package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理,用于处理程序未能处理到到异常
 * Create by qs on 2018/8/9
 * email:qinsen@chinaredsun.com
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req,Exception exception) {
        log.error("---DefaultException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), exception.getMessage());
        return exception.getMessage();
    }
}
