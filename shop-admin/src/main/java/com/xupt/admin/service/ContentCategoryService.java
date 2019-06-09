package com.xupt.admin.service;

import com.xupt.common.dto.ResultMap;
import com.xupt.domain.ContentCategory;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
public interface ContentCategoryService {
    List<ContentCategory> findAll();

    List<ContentCategory> findTreeData(Long pid);

    ContentCategory getCategoryById(Long id);

    ResultMap save(ContentCategory category);

    ResultMap delete(Long id);
}
