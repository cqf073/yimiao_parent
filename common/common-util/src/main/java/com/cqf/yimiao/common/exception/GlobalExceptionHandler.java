package com.cqf.yimiao.common.exception;

import com.cqf.yimiao.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/20 17:59
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常执行处
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }
    //自定义异常执行处
    @ExceptionHandler(YimiaoException.class)
    @ResponseBody
    public Result error(YimiaoException e){
        e.printStackTrace();
        return Result.fail();
    }
}
