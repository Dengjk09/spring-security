package com.dengjk.springsecuritydemo.commone.exception;

import com.dengjk.springsecuritydemo.commone.utils.ResultData;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerHandle {
    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler({BindException.class,MethodArgumentNotValidException.class})
    public ResultData validExceptionHandler(Exception e) {
        String errMsg =null;
        if (e instanceof BindException){
            errMsg = ((BindException) e).getBindingResult().getFieldErrors()
                    .stream().map(error -> error.getField() + ":" + error.getDefaultMessage())
                    .collect(Collectors.joining(","));
        }else if(e instanceof MethodArgumentNotValidException) {
            errMsg = ((MethodArgumentNotValidException)e).getBindingResult().getFieldErrors()
                    .stream().map(error -> error.getField() + ":" + error.getDefaultMessage())
                    .collect(Collectors.joining(","));
        }
        logger.error(errMsg.toString());
        return ResultData.CHECK_FAIL(errMsg);
    }

    @ExceptionHandler(value ={Exception.class,Exception.class})
    @ResponseBody
    public ResultData defaultErrorHandler(Exception e) {
        logger.error("", e);
        return  ResultData.CHECK_FAIL(e.getMessage());
    }
}
