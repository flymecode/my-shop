package com.xupt.admin.controller;

import com.xupt.admin.service.ContentCategoryService;
import com.xupt.admin.utils.SortList;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.content.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Controller
@RequestMapping("/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping
    public String listContentCategory(Model model) {
        List<ContentCategory> target = new ArrayList<>();
         List<ContentCategory> sources = contentCategoryService.findAll();
        SortList.sort(sources, target, 0L);
        model.addAttribute("content", target);
        return "content_category";
    }
    @ResponseBody
    @PostMapping("/data")
    public List<ContentCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        List<ContentCategory> list = contentCategoryService.findTreeData(id);
        return list;
    }

    @GetMapping("/{id}/sub")
    public String form(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
        ContentCategory contentCategory = contentCategoryService.getCategoryById(id);
        redirectAttributes.addFlashAttribute("c",contentCategory);
        return "redirect:/category/sub";
    }

    @GetMapping("/{id}/edit")
    public String eidt(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        ContentCategory contentCategory = contentCategoryService.getCategoryById(id);
        redirectAttributes.addFlashAttribute("c",contentCategory);
        return "redirect:/category/sub";
    }

    @PostMapping("/save")
    public String save(ContentCategory category, Model model,RedirectAttributes redirectAttributes) {
        ResultMap save = contentCategoryService.save(category);
        if (save.getCode() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", save);
            return "redirect:/category/";
        } else {
            model.addAttribute("baseResult", save);
            return "category_form";
        }

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,Model model) {
        ResultMap delete = contentCategoryService.delete(id);
        if (delete.getCode() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", delete);
            return "redirect:/category/";
        } else {
            model.addAttribute("baseResult", delete);
            return "category_form";
        }

    }

}
