package com.xupt.admin.controller;

import com.xupt.admin.service.ItemContentService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.item.ItemForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author maxu
 * @date 2019/6/11
 */
@RequestMapping("/itemc")
@Slf4j
@Controller
public class ItemContentController {
    @Autowired
    private ItemContentService contentService;

    @PostMapping("/save")
    public String saveContent(@Valid ItemForm content, Model model , BindingResult bindingResult) {
        log.info(content.toString());
        if (bindingResult.hasErrors()) {
        }
        ResultMap resultMap = contentService.saveContent(content);
        model.addAttribute("contentResult", resultMap);
        return "item_list";
    }

    @ResponseBody
    @GetMapping("/page")
    public ResponseEntity listContentPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                                          @RequestParam(value = "length",defaultValue = "10") Integer length,
                                          @RequestParam(value = "draw",defaultValue = "1") Integer draw) {
        ResultMap resultMap = contentService.listContents(start, length, draw);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }

}
