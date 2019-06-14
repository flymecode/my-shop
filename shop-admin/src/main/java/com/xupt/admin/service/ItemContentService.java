package com.xupt.admin.service;

import com.xupt.common.dto.ResultMap;
import com.xupt.domain.item.ItemForm;

/**
 * @author maxu
 * @date 2019/6/11
 */
public interface ItemContentService {
    ResultMap saveContent(ItemForm content);

    ResultMap listContents(Integer start, Integer length, Integer draw);
}
