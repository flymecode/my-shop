package com.xupt.admin.service.impl;

import com.xupt.admin.mapper.ItemCategoryMapper;
import com.xupt.admin.service.ItemService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.content.ContentCategory;
import com.xupt.domain.item.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Resource
    private ValueOperations<String, List<ItemCategory>> valueOperations;

    @Override
    public List<ItemCategory> findAll() {
        List<ItemCategory> item = valueOperations.get("item");
        if (item == null) {
            item = itemCategoryMapper.findAll();
            valueOperations.set("item",item,30, TimeUnit.MICROSECONDS);
        }
        return item;
    }

    @Override
    public List<ItemCategory> findTreeData(Long pid) {
        return itemCategoryMapper.findTreeData(pid);
    }

    @Override
    public ItemCategory getCategoryById(Long id) {
        return itemCategoryMapper.findById(id);
    }

    @Override
    public ResultMap save(ItemCategory category) {
        ResultMap result = new ResultMap();
        Date date = new Date();
        category.setStatus(1);
        category.setUpdated(date);
        category.setCreated(date);
        category.setIsParent(false);
        // 设置为根目录
        if (category.getParentId() == null) {
            category.setParentId(0L);
            category.setIsParent(true);
        } else if (category.getParentId() != 0){
            ContentCategory cp = itemCategoryMapper.isParent(category.getParentId());
            if (cp != null && !cp.getIsParent()) {
                itemCategoryMapper.updateParent(category.getParentId());
            }
        }
        itemCategoryMapper.insert(category);
        result.message("保存成功");
        return result;
    }

    @Override
    public ResultMap delete(Long id) {
        ResultMap resultMap = new ResultMap();
        int isDelete = itemCategoryMapper.deleteById(id);
        resultMap.success().message("删除成功");
        return resultMap;
    }
}
