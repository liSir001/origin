package com.lisir.cn.advice;

import com.lisir.cn.entity.Error;
import com.lisir.cn.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @Auther: liSir
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    public Error myExceptionHandler(MyException ex) {
        log.error("全局异常信息 ex:{}", ex);
        final int code = ex.getErrorCode().code;
        final String message = ex.getErrorCode().msg;
        return Error.builder().code(code).message(message).build();
    }
}
