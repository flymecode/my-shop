package com.xupt.api.controller;

import com.xupt.api.constant.Constants;
import com.xupt.api.service.FavoriteService;
import com.xupt.common.dto.ResultMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author maxu
 * @date 2019/6/13
 */
@RestController
@RequestMapping(Constants.VERSION + "/star")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    @PostMapping
    public ResponseEntity start(String id, HttpServletRequest request) {
        ResultMap resultMap = favoriteService.favorite(id,request);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }

    @GetMapping
    @ApiOperation("获取收藏")
    public ResponseEntity get(HttpServletRequest request) {
        ResultMap resultMap = favoriteService.listFavorite(request);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
