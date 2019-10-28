package com.lisir.cn.job.impl;

import com.lisir.cn.job.JobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Auther: liSir
 * @Date: 2019/9/17 19:58
 */
@Slf4j
@Component("MyJobExecutor")
public class MyJobExecutor implements JobExecutor {
    @Override
    public void run() {
        System.out.println("当前时间:" + LocalDateTime.now());
    }
}
