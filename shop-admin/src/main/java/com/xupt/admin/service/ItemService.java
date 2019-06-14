package com.xupt.admin.service;

import com.xupt.common.dto.ResultMap;
import com.xupt.domain.item.ItemCategory;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/11
 */
public interface ItemService {
    List<ItemCategory> findAll();

    List<ItemCategory> findTreeData(Long pid);

    ItemCategory getCategoryById(Long id);

    ResultMap save(ItemCategory category);

    ResultMap delete(Long id);
}
