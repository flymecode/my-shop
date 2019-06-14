package com.xupt.api.service;

import com.xupt.common.dto.ResultMap;

import javax.servlet.http.HttpServletRequest;

/**
 * @author maxu
 * @date 2019/6/13
 */
public interface FavoriteService {
    ResultMap favorite(String id, HttpServletRequest request);

    ResultMap listFavorite(HttpServletRequest request);
}
