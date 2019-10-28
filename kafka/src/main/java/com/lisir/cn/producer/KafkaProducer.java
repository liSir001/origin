package com.lisir.cn.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生产者
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.app.topic}")
    private String topic;

    /**
     * send 定时任务 每10s发送一次
     */
    @Scheduled(cron = "00/10 * * * * ?")
    public void send() {
        String message = "kafka message " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("topic=" + topic + ", message=" + message);
        ListenableFuture send = kafkaTemplate.send(topic, message);
        send.addCallback(success -> System.out.println("【生产者】消息发送:" + message),
                fail -> System.out.println("【生产者】消息发送失败:" + message));
    }

}