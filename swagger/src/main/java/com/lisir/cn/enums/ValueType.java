package com.lisir.cn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValueType {

    TEXT("文本"),
    FLOAT("浮点数"),
    INTEGER("整数"),
    BOOLEAN("布尔"),
    DATE("日期(yyyy-MM-dd)"),
    DATETIME("日期时间(yyyy-MM-dd HH:mm:ss)"),
    ;

    private String desc;
}
