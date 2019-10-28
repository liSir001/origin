package com.lisir.cn.util;

import com.alibaba.fastjson.JSON;
import com.lisir.cn.exception.SendKafkaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;

/**
 * 发送kafka工具类
 * @Auther: liSir
 */
@Component
@Slf4j
public class SendKafkaUtil {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static SendKafkaUtil instance;

    @PostConstruct
    public void init() {
        SendKafkaUtil.instance = this;
    }

    /**
     * 发送消息到topic
     * @param topic 发送的topic
     * @param obj   消息
     */
    public static void sendMessage(String topic, Object obj) {
        try {
            ListenableFuture<SendResult<String, Object>> send = instance.kafkaTemplate.send(topic, obj);
            final SendResult<String, Object> result = send.get();
            if (!result.getRecordMetadata().hasOffset()) {
                throw new SendKafkaException(String.format("send kafka topic fail, topic=%s, message=%s", topic, JSON.toJSONString(obj)));
            }
        } catch (Exception e) {
            throw new SendKafkaException(String.format("send kafka topic fail, topic=%s, message=%s", topic, JSON.toJSONString(obj)), e);
        }
    }
}