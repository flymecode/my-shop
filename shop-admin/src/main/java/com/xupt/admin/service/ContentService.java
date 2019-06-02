package com.xupt.admin.service;

import com.github.pagehelper.PageInfo;
import com.xupt.domain.Content;

/**
 * @author maxu
 * @date 2019/6/2
 */
public interface ContentService {

    Content getContent(Integer id);

    void updateContent(Integer id);

    PageInfo<Content> listContents(Integer start, Integer length);

    void deleteContents(String ids);
}
