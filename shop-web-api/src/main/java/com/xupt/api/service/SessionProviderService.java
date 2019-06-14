package com.xupt.api.service;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Service
public class SessionProviderService {

    @Resource
    private ValueOperations<String, String> valueOperations;

    public String getAttributterForUsername(String cSessionId) {
        String value = valueOperations.get(cSessionId + ":USER_NAME");
        if (null != value) {
            //计算session过期时间是 用户最后一次请求开始计时.
            valueOperations.set(cSessionId + ":USER_NAME", value, 60 * 60, TimeUnit.MINUTES);
            return value;
        }
        return null;
    }
}
