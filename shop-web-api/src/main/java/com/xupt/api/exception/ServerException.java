package com.xupt.api.exception;

/**
 * @author maxu
 * @date 2019/6/5
 */
public class ServerException  extends RuntimeException{
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
