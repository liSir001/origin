package com.lisir.cn.exception;

public class EasyExcelException extends RuntimeException {

    private static final long serialVersionUID = -5456062088984840434L;

    public EasyExcelException() {
        super();
    }

    public EasyExcelException(String message) {
        super(message);
    }

    public EasyExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyExcelException(Throwable cause) {
        super(cause);
    }
}
