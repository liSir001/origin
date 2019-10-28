package com.lisir.cn.exception;

/**
 * @author lilei
 */
public class JsonDeserializeException extends RuntimeException {

    public JsonDeserializeException() {
        super();
    }

    public JsonDeserializeException(String message) {
        super(message);
    }

    public JsonDeserializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonDeserializeException(Throwable cause) {
        super(cause);
    }
}
