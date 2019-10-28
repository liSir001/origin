package com.lisir.cn.entity;

import lombok.AllArgsConstructor;

/**
 * 异常枚举
 * @Auther: lilei
 */
@AllArgsConstructor
public enum ErrorCode {

    DICTIONARY_NOT_FOUND(10001, "字典找不到"),
    DICTIONARY_NOT_EXIST(10002, "字典不存在"),
    PARAM_ERROR(10010, ""),
    SERVER_ERROR(99999, "系统内部异常"),
    ;

    public int code;

    public String msg;
}
