package com.xupt.admin.controller;

import com.xupt.admin.service.ContentCategoryService;
import com.xupt.admin.utils.SortList;
import com.xupt.domain.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Controller
@RequestMapping("/content")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping("/category")
    public String listContentCategory(Model model) {
        List<ContentCategory> target = new ArrayList<>();
        List<ContentCategory> sources = contentCategoryService.findAll();
        SortList.sort(sources, target, 0L);
        model.addAttribute("content", target);
        return "content_category";
    }
    @ResponseBody
    @PostMapping("/data")
    public List<ContentCategory> treeData(Long pid) {
        if (pid == null) {
            pid = 0L;
        }
        List<ContentCategory> list = contentCategoryService.findTreData(pid);
        return list;
    }
}
