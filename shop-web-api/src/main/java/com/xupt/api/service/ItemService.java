package com.xupt.api.service;

import com.xupt.common.dto.ResultMap;

/**
 * @author maxu
 * @date 2019/6/12
 */
public interface ItemService {
    ResultMap findItems(Long cid);
}
