package com.lisir.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liSir
 * @Date: 2019/9/24 15:29
 */
@RestController
@Slf4j
public class LogController {

    @GetMapping("logging")
    public String testLog(){
        log.info("TestLog start");
        return "Hello Log4j2!";
    }
}
