package com.xupt.admin.exception;

/**
 * @author maxu
 * @date 2019/6/4
 */
public class ExcelException extends RuntimeException {
    private String message;

    public ExcelException(String message) {
        super(message);
    }
}
