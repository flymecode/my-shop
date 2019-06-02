package com.xupt.admin.service;

import com.xupt.domain.ContentCategory;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
public interface ContentCategoryService {
    List<ContentCategory> findAll();

    List<ContentCategory> findTreData(Long pid);
}
