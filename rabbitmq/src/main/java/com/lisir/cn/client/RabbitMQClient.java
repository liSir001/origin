package com.lisir.cn.client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 发送消息
 * @Auther: liSir
 */
@Component
@EnableScheduling
public class RabbitMQClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "00/10 * * * * ?")
    public void send() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000; i++) {
            final String message = "hello";
            rabbitTemplate.convertAndSend("exchange", message);
            stopWatch.stop();
            System.out.println("发送消息耗时: " + stopWatch.getTotalTimeMillis());
        }
    }
}
