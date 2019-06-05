package com.xupt.api.controller;

import com.xupt.api.constant.Commons;
import com.xupt.api.service.ContentService;
import com.xupt.common.dto.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxu
 * @date 2019/6/5
 */
@RestController
@RequestMapping(Commons.VERSION + "/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public ResponseEntity getContentById(@PathVariable("id") Long id) {
        ResultMap resultMap = contentService.findContentById(id);
        System.out.println(resultMap);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}

