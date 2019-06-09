package com.xupt.ui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping("index")
    public String index() throws Exception {
//        List<ContentDTO> contents = ContentAPI.findContentByCategoryId(89L);
//        log.info(contents.toString());
        return null;
    }
}
