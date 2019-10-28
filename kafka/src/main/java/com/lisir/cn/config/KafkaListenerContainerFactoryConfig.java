package com.lisir.cn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

/**
 * 自定义消费者
 * @Auther: liSir
 */
@Configuration
public class KafkaListenerContainerFactoryConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean("listenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainerFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        KafkaProperties.Listener listener = kafkaProperties.getListener();
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        // 指定使用DefaultKafkaConsumerFactory
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(props));
        factory.getContainerProperties().setAckMode(listener.getAckMode());
        return factory;
    }
}
