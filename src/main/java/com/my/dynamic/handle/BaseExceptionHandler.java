package com.my.dynamic.handle;


import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.exception.CommonException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice

public class BaseExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response,Exception e){
        if (e.getClass() == CommonException.class){
            CommonException ce=(CommonException) e;
            Result result=new Result(ce.getResultCode());
            return result;
        }else {
            Result result=new Result(ResultCode.SERVER_ERROR);
            return result;
        }
    }

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Result error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e){
        return new Result(ResultCode.UNAUTHORISE);
    }

}
