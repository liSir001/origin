package com.lisir.cn.service;

import com.lisir.cn.util.SendKafkaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: liSir
 */
@Service
public class KafkaService {

    @Value("${kafka.app.topic}")
    private String topic;

    public void sendMessage(){
        final String message = "hello";
        SendKafkaUtil.sendMessage(topic, message);
    }
}
