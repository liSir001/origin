package com.lisir.cn.controller;

import com.lisir.cn.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping
    public void sendMessage() {
        kafkaService.sendMessage();
    }
}
