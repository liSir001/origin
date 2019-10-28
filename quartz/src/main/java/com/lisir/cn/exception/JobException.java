package com.lisir.cn.exception;

/**
 * @Auther: liSir
 * @Date: 2019/9/17 19:52
 */
public class JobException extends RuntimeException {

    public JobException() {
        super();
    }

    public JobException(String message) {
        super(message);
    }

    public JobException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobException(Throwable cause) {
        super(cause);
    }
}
