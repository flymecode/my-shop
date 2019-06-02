package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.ContentMapper;
import com.xupt.admin.service.ContentService;
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
    public void updateContent(Integer id) {

    }

    @Override
    public PageInfo<Content> listContents(Integer start, Integer length) {
        PageHelper.offsetPage(start, length);
        List<Content> contents = contentMapper.searchContents();
        PageInfo<Content> pages = PageInfo.of(contents);
        if (contents != null) {
            return pages;
        } else {
            throw new RuntimeException("异常");
        }
    }

    @Override
    public void deleteContents(String ids) {

    }
}
