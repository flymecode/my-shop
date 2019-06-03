package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.ContentMapper;
import com.xupt.admin.service.ContentService;
import com.xupt.admin.validator.ContentForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.Content;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Content getContent(Integer id) {
        return null;
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

        return null;
    }

    @Override
    public ResultMap saveContent(ContentForm content) {
        return null;
    }
}
