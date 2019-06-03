package com.xupt.admin.service;

import com.xupt.admin.validator.ContentForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.Content;

/**
 * @author maxu
 * @date 2019/6/2
 */
public interface ContentService {

    Content getContent(Integer id);

    ResultMap updateContent(Integer id);

    ResultMap listContents(Integer integer, Integer start, Integer length);

    ResultMap deleteContents(String ids);

    ResultMap saveContent(ContentForm content);
}
