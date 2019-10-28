package com.lisir.cn.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import java.io.Serializable;

public class OutputService implements JavaDelegate, Serializable {

    // 流程变量
    private Expression text;

    @Override
    public void execute(DelegateExecution execution) {
        // 获取流程变量
        String value = (String) text.getValue(execution);
        System.out.println("resourceId = " + execution.getCurrentActivityId());
        // TODO 流程变量落库
        String output = value.toUpperCase();
        execution.setVariable("result", output);
    }
}
