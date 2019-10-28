package com.lisir.cn.exception;

import com.lisir.cn.entity.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * 自定义异常
 *
 * @Auther: liSir
 */
@Data
@AllArgsConstructor
public class MyException extends RuntimeException{

    @NonNull
    private ErrorCode errorCode;
}
