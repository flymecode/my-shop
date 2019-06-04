package com.xupt.admin.service;


import com.xupt.common.dto.ResultMap;

/**
 * @author maxu
 * @date 2019/6/3
 */
public interface IQiniuService {
    ResultMap upload(byte[] data);
}
