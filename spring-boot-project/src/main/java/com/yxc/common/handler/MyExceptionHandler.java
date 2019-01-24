package com.yxc.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//控制器增强用来处理异常
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    //专门处理自定义异常
    //绑定异常类
    @ExceptionHandler(SbpRuntimeException.class)
    @ResponseBody
    public Result handleSbpRuntimeException(HttpServletRequest request, SbpRuntimeException ex) {
        Result response;
        log.error("StudentException code:{},msg:{}",ex.getResponse().getCode(),ex.getResponse().getMsg());
        response = new Result(ex.getResponse().getCode(),ex.getResponse().getMsg());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(HttpServletRequest request, Exception ex) {
        Result response;
        log.error("exception error:{}",ex);
        response = new Result(ErrorCodeAndMsg.SYSTEM_ERROR.getCode(), ErrorCodeAndMsg.SYSTEM_ERROR.getMsg());
        return response;
    }
}
