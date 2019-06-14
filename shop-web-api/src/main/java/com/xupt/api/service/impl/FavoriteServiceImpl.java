package com.xupt.api.service.impl;

import com.xupt.api.constant.Constants;
import com.xupt.api.mapper.FavoriteMapper;
import com.xupt.api.service.FavoriteService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.item.Item;
import com.xupt.domain.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/13
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Override
    public ResultMap favorite(String id, HttpServletRequest request) {
        ResultMap resultMap = new ResultMap();
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute(Constants.USER_SESSION);
        if (userDTO != null) {
            favoriteMapper.insert(id, userDTO.getId());
        }
        return resultMap.success().message("收藏成功");
    }

    @Override
    public ResultMap listFavorite(HttpServletRequest request) {
        ResultMap resultMap = new ResultMap();
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(Constants.USER_SESSION);
        List<Item> list = null;
        if (userDTO != null) {
             list = favoriteMapper.search(userDTO.getId());
        }
        return resultMap.success().payloads(list);
    }
}
