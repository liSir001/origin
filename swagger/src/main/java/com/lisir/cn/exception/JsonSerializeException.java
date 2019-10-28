package com.lisir.cn.exception;

/**
 * @author lilei
 */
public class JsonSerializeException extends RuntimeException {

    public JsonSerializeException() {
        super();
    }

    public JsonSerializeException(String message) {
        super(message);
    }

    public JsonSerializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonSerializeException(Throwable cause) {
        super(cause);
    }
}
