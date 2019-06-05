package com.xupt.ui.api;

import com.xupt.common.untils.HttpClientUtils;
import com.xupt.common.untils.MapperUtils;
import com.xupt.common.dto.ContentDTO;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/5
 */
public class ContentAPI {
    public static List<ContentDTO> findContentByCategoryId(Long id) throws Exception {
        String result = HttpClientUtils.doGet(API.API_CONTENT + id);
        System.out.println(result);
        return MapperUtils.json2listByTree(result, "data", ContentDTO.class);
    }
}
