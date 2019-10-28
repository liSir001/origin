package com.lisir.cn.server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受消息
 *
 * @Auther: liSir
 */
@Component
public class RabbitMQListener {

    @RabbitListener(queues = "exchange")
    public void onMessage(String message) {
        System.out.println("receive=" + message);
    }
}
