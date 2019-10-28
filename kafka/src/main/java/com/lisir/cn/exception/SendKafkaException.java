package com.lisir.cn.exception;

/**
 * @Auther: liSir
 */
public class SendKafkaException extends RuntimeException {

    public SendKafkaException() {
        super();
    }

    public SendKafkaException(String message) {
        super(message);
    }

    public SendKafkaException(Throwable cause) {
        super(cause);
    }

    public SendKafkaException(String message, Throwable cause) {
        super(message, cause);
    }
}
