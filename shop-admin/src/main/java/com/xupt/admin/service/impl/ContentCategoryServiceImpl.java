package com.xupt.admin.service.impl;

import com.xupt.admin.mapper.ContentCategoryMapper;
import com.xupt.admin.service.ContentCategoryService;
import com.xupt.domain.ContentCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Resource
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCategory> findAll() {
        return contentCategoryMapper.findAll();
    }

    @Override
    public List<ContentCategory> findTreData(Long pid) {
        return contentCategoryMapper.findTreeData(pid);
    }

}
