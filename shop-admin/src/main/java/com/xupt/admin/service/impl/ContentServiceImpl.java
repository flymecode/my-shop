package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.ContentMapper;
import com.xupt.admin.service.ContentService;
import com.xupt.admin.validator.ContentForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.content.Content;
import com.xupt.domain.content.ContentCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;

    @Override
    public ResultMap getContent(Integer id) {
        ResultMap resultMap = new ResultMap();
        Content content = contentMapper.searchContent(id);
        ContentCategory contentCategory = content.getContentCategory();
        if (StringUtils.isNotEmpty(contentCategory.getName())) {
            contentCategory.setName("/");
        }
        resultMap.payload(content);
        return resultMap;
    }

    @Override
    public ResultMap updateContent(Integer id) {

        return null;
    }

    @Override
    public ResultMap listContents(Integer start, Integer length,Integer draw) {
        ResultMap result = new ResultMap();
        PageHelper.offsetPage(start, length);
        List<Content> contents = contentMapper.searchContents();
        PageInfo<Content> pages = PageInfo.of(contents);
        result.payload(pages.getList());
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotal());
        result.put("recordsFiltered",pages.getTotal());
        result.put("error","");
        return result;
    }

    @Override
    public ResultMap deleteContents(String ids) {
        ResultMap resultMap = new ResultMap();
        contentMapper.deleteContent(ids);
        resultMap.message("删除成功");
        return resultMap;
    }

    @Override
    public ResultMap saveContent(ContentForm content) {
        ResultMap resultMap = new ResultMap();
        Content tbContent = new Content();
        BeanUtils.copyProperties(content, tbContent);
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        contentMapper.saveContent(tbContent);
        return resultMap;
    }
}
