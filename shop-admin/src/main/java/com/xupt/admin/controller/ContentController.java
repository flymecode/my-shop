package com.xupt.admin.controller;

import com.github.pagehelper.PageInfo;
import com.xupt.admin.service.ContentService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.Content;
import com.xupt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

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
    public String saveUser(User user, Model model) {
        ResultMap fail = ResultMap.fail();
        model.addAttribute("contentResult", fail);
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
        contentService.updateContent(id);
        ResultMap success = ResultMap.success();
        redirectAttributes.addFlashAttribute("baseResult", success);
        return "redirect:/content/list";
    }

    @PostMapping("/search")
    public String searchUsers() {
        log.info("来了");
        return "content_list";
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResultMap deleteUsers(@RequestParam("ids") String ids) {
        log.info(ids);
        ResultMap success = ResultMap.fail();
        contentService.deleteContents(ids);
        return success;
    }
    @ResponseBody
    @GetMapping("/page")
    public Map<String,Object> listUsersPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                                            @RequestParam(value = "length",defaultValue = "10") Integer length,
                                            @RequestParam(value = "draw",defaultValue = "1") Integer draw) {
        PageInfo<Content> contents = contentService.listContents(start, length);
        Map<String, Object> result = new HashMap<>();
        result.put("draw",draw);
        result.put("recordsTotal",contents.getTotal());
        result.put("recordsFiltered",contents.getTotal());
        result.put("data",contents.getList());
        result.put("error","");
        return result;
    }
}
