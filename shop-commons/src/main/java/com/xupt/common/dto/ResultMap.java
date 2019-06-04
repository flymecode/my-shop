package com.xupt.common.dto;

import com.xupt.common.enums.HttpCodeEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author maxu
 * @date 2019/5/29
 */
public class ResultMap extends HashMap<String,Object> implements Serializable {
    private HashMap<String, Object> header;

    private int code = HttpCodeEnum.OK.getCode();

    public ResultMap() {

    }
    public ResultMap success() {
        this.code = HttpCodeEnum.OK.getCode();
        this.header = new HashMap<>();
        this.header.put("code", this.code);
        this.header.put("msg", "Success");
        this.put("header", header);
        this.put("data", "");
        return this;
    }

    public ResultMap fail() {
        this.code = HttpCodeEnum.FAIL.getCode();
        this.header = new HashMap<>();
        this.header.put("code", code);
        this.put("header", header);
        this.put("payload", "");
        return this;
    }

    public ResultMap fail(int code) {
        this.code = code;
        this.header = new HashMap<>();
        this.header.put("code", code);
        this.put("header", header);
        this.put("payload", "");
        return this;
    }

    public ResultMap message(String message) {
        this.header = new HashMap<>();
        this.header.put("msg", message);
        this.put("header", header);
        return this;
    }

    public ResultMap payload(Object object) {
        this.put("data", null == object ? "" : object);
        return this;
    }

    public ResultMap payloads(Collection list) {
        this.put("data", null == list ? new ArrayList() : list);
        return this;
    }

    public int getCode() {
        return code;
    }
}
