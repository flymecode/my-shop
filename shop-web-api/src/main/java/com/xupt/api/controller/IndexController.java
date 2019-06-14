package com.xupt.api.controller;

import com.xupt.api.constant.Constants;
import com.xupt.api.service.ContentService;
import com.xupt.api.service.ItemService;
import com.xupt.common.dto.ResultMap;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author maxu
 * @date 2019/6/5
 */
@RestController
@RequestMapping(Constants.VERSION + "/content")
@CrossOrigin
@Slf4j
public class IndexController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ItemService itemService;

    /**
     * 获取轮播图
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("get content by id")
    public ResponseEntity getContentById(@PathVariable("id") Long id) {
        ResultMap resultMap = contentService.findContentById(id);
        log.info(resultMap.toString());
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }

    @ApiOperation("get hot shopping")
    @GetMapping("/hot/{id}")
    public ResponseEntity getCategory(@PathVariable("id") Long id) {
        ResultMap resultMap = itemService.findItems(id);
        log.info(resultMap.toString());
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}

