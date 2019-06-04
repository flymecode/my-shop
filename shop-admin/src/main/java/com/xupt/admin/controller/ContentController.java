package com.xupt.admin.controller;

import com.xupt.admin.service.ContentService;
import com.xupt.admin.validator.ContentForm;
import com.xupt.common.dto.ResultMap;
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
        log.info(content.toString());
        if (bindingResult.hasErrors()) {
        }
        ResultMap resultMap = contentService.saveContent(content);
        model.addAttribute("contentResult", resultMap);
        return "content_list";
    }

    @GetMapping("/{id}/edit")
    public String getContent(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ResultMap content = contentService.getContent(id);
        redirectAttributes.addFlashAttribute("content", content);
        return "redirect:/content/form";
    }
    @ResponseBody
    @GetMapping("/{id}/detail")
    public ResultMap getContentDetail(@PathVariable("id") Integer id) {
        ResultMap content = contentService.getContent(id);
        log.info(content.toString());
        return content;
    }
    @PostMapping("/{id}/update")
    public String updateContent(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ResultMap  resultMap = contentService.updateContent(id);
        redirectAttributes.addFlashAttribute("contentResult", resultMap);
        return "redirect:/content/list";
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity deleteContents(@RequestParam("ids") String ids) {
        ResultMap resultMap = contentService.deleteContents(ids);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
    @ResponseBody
    @GetMapping("/page")
    public ResponseEntity listContentPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                                        @RequestParam(value = "length",defaultValue = "10") Integer length,
                                        @RequestParam(value = "draw",defaultValue = "1") Integer draw) {
        ResultMap resultMap = contentService.listContents(start, length, draw);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }

    @GetMapping("/{id}/delete")
    public String deleteContent(@PathVariable("id") String id,RedirectAttributes redirectAttributes) {
        ResultMap resultMap = contentService.deleteContents(id);
        redirectAttributes.addFlashAttribute("contentResult", resultMap);
        return "redirect:/content/list";
    }
}
