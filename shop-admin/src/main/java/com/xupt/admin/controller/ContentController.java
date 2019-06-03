package com.xupt.admin.controller;

import com.xupt.admin.service.ContentService;
import com.xupt.admin.validator.ContentForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Controller
@RequestMapping("/content")
@Slf4j
public class ContentController {
    @Autowired
    private ContentService contentService;


    @PostMapping("/save")
    public String saveContent(@Valid ContentForm content, Model model , BindingResult bindingResult) {
        ResultMap resultMap = contentService.saveContent(content);
        model.addAttribute("contentResult", resultMap);
        return "content_list";
    }

    @GetMapping("/{id}/edit")
    public String getUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Content content = contentService.getContent(id);
        redirectAttributes.addFlashAttribute("content", content);
        return "redirect:/content/form";
    }
    @ResponseBody
    @GetMapping("/{id}/detail")
    public Content getUserDetail(@PathVariable("id") Integer id) {
        Content content = contentService.getContent(id);
        return content;
    }
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ResultMap  resultMap = contentService.updateContent(id);
        redirectAttributes.addFlashAttribute("baseResult", resultMap);
        return "redirect:/content/list";
    }

    @PostMapping("/search")
    public String searchUsers() {
        log.info("来了");
        return "content_list";
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity deleteUsers(@RequestParam("ids") String ids) {
        ResultMap resultMap = contentService.deleteContents(ids);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
    @ResponseBody
    @GetMapping("/page")
    public ResponseEntity listUsersPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                                        @RequestParam(value = "length",defaultValue = "10") Integer length,
                                        @RequestParam(value = "draw",defaultValue = "1") Integer draw) {
        ResultMap resultMap = contentService.listContents(start, length, draw);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
