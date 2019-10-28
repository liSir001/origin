package com.lisir.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EasyExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyExcelApplication.class, args);
    }
}
