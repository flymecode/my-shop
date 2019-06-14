package com.xupt.api.service.impl;

import com.xupt.api.mapper.ContentMapper;
import com.xupt.api.service.ContentService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.content.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;


    @Override
    public ResultMap findContentById(Long id) {
        List<Content> contents = contentMapper.selectContentByCategoryId(id);
        System.out.println(contents);
        ResultMap resultMap = new ResultMap().success();
        return resultMap.payload(contents);
    }

}
