package com.xupt.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Data
public class ResultMap implements Serializable {
    private int status;
    private String message;

    public static ResultMap success() {
        return createResultMap(200,"成功");
    }
    public static ResultMap fail() {
        return createResultMap(500, "失败");
    }
    public static ResultMap success(String message) {
        return createResultMap(200, message);
    }
    public static ResultMap success(int status,String message) {
        return createResultMap(status, message);
    }
    public static ResultMap fail(String message) {
        return createResultMap(500, message);
    }
    public static ResultMap fail(int status,String message) {
        return createResultMap(status, message);
    }

    private static ResultMap createResultMap(int status, String message) {
        ResultMap resultMap = new ResultMap();
        resultMap.setStatus(status);
        resultMap.setMessage(message);
        return resultMap;
    }
}
