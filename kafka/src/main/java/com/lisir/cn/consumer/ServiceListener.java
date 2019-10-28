package com.lisir.cn.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 消费者
 * @author liSir
 */
@Component
@Slf4j
public class ServiceListener {

    @KafkaListener(topics = "${kafka.app.topic}")
    public void onMessage(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.debug("topic={}, partition={}, offset={}", record.topic(), record.partition(), record.offset());
        final long startTime = System.currentTimeMillis();
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        if (!kafkaMessage.isPresent()) {
            log.warn("message is empty, topic={}, partition={}, offset={}", record.topic(), record.partition(), record.offset());
        } else {
            // TODO 具体逻辑处理
            String message = kafkaMessage.get();
            System.out.println("【消费者】消费消息:" + message);
        }
        ack.acknowledge(); // 手动提交偏移量
        log.info("topic={}, partition={}, offset={}, cos={}", record.topic(), record.partition(), record.offset(), (System.currentTimeMillis() - startTime));
    }
}