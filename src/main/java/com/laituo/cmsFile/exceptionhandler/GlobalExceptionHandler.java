package com.laituo.cmsFile.exceptionhandler;


import com.laituo.cmsFile.common.R;

import com.laituo.cmsFile.common.ResultCode;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {

    //指定异常执行方法
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R error(MissingServletRequestParameterException e){
        log.error("运行时异常：----------------{}", e);
        return R.fail(ResultCode.BAD,e.getParameterName()+"不可为空");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R error(MethodArgumentNotValidException e){
        log.error("运行时异常：----------------{}", e);
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuffer buffer = new StringBuffer();
        for (ObjectError error : errors) {
            buffer.append(error.getDefaultMessage()).append(" ");
        }
        return R.fail(ResultCode.BAD,buffer.toString());
    }
    /**
     * 参数类型转换错误
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public R parameterTypeException(HttpMessageConversionException e) {
        log.error("运行时异常：----------------{}", e);
        return R.fail(ResultCode.BAD,"类型转换错误");
    }

    /**
     * 空异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R nullException(NullPointerException e){
        log.error("运行时异常：------------------{}",e);
        return R.fail(ResultCode.Error,"空异常");
    }

    /**
     * Token格式不正确
     */
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public R error(MalformedJwtException e){
        e.printStackTrace();
        return R.fail(ResultCode.UN_LOGIN,"未登录");
    }
//    shiro验证
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public R handler(UnauthenticatedException e) {
        log.error("运行时异常：----------------{}", e);
        return R.fail(ResultCode.UN_LOGIN,"未登录");
    }
    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public R handler(UnknownAccountException e) {
        log.error("运行时异常：----------------{}", e);
        return R.fail(ResultCode.UN_LOGIN,"未登录");
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public R handler(UnauthorizedException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return R.fail(ResultCode.FID,"无权限");
    }
//shiro验证结束

    /**
     * 时间转换异常
     */
    @ExceptionHandler(value = ParseException.class)
    @ResponseBody
    public R handler(ParseException e){
        log.error("运行时异常：------------------{}",e);
        return R.fail(ResultCode.Error,"时间转换错误");
    }









    //自定义的异常处理
//    @ExceptionHandler(GuliException.class)
//    @ResponseBody
//    public R error(GuliException e){
//        log.error(e.getMessage());
//        e.printStackTrace();
//        return R.error().code(e.getCode()).message(e.getMsg());
//    }

}
