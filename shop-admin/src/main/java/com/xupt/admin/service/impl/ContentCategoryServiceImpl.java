package com.xupt.admin.service.impl;

import com.xupt.admin.mapper.ContentCategoryMapper;
import com.xupt.admin.service.ContentCategoryService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.ContentCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<ContentCategory> findTreeData(Long pid) {
        return contentCategoryMapper.findTreeData(pid);
    }

    @Override
    public ContentCategory getCategoryById(Long id) {
        return contentCategoryMapper.findById(id);
    }

    @Override
    public ResultMap save(ContentCategory category) {
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
            ContentCategory cp = contentCategoryMapper.isParent(category.getParentId());
            if (cp != null && !cp.getIsParent()) {
                contentCategoryMapper.updateParent(category.getParentId());
            }
        }
        contentCategoryMapper.insert(category);
        result.message("保存成功");
        return result;
    }

    @Override
    public ResultMap delete(Long id) {
        ResultMap resultMap = new ResultMap();
        int isDelete = contentCategoryMapper.deleteById(id);
        resultMap.success().message("删除成功");
        return resultMap;
    }
    public ResultMap update (ContentCategory category) {
        return null;
    }
}
