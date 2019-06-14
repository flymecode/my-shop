package com.xupt.admin.controller;

import com.xupt.admin.service.ItemService;
import com.xupt.admin.utils.SortList;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.item.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Controller
@RequestMapping("/item")
public class ItemCategoryController {

    @Autowired
    private ItemService itemService;
    @GetMapping
    public String listItemCategory(Model model) {
        List<ItemCategory> target = new ArrayList<>();
        List<ItemCategory> sources = itemService.findAll();
        SortList.sortItem(sources, target, 0L);
        model.addAttribute("content", target);
        return "item_category";
    }

    @ResponseBody
    @PostMapping("/data")
    public List<ItemCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        List<ItemCategory> list = itemService.findTreeData(id);
        return list;
    }

    @GetMapping("/{id}/sub")
    public String form(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        ItemCategory itemCategory = itemService.getCategoryById(id);
        redirectAttributes.addFlashAttribute("c",itemCategory);
        return "redirect:/category/sub";
    }

    @GetMapping("/{id}/edit")
    public String eidt(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        ItemCategory contentCategory = itemService.getCategoryById(id);
        redirectAttributes.addFlashAttribute("c",contentCategory);
        return "redirect:/category/sub";
    }

    @PostMapping("/save")
    public String save(ItemCategory category, Model model,RedirectAttributes redirectAttributes) {
        ResultMap save = itemService.save(category);
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
        ResultMap delete = itemService.delete(id);
        if (delete.getCode() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", delete);
            return "redirect:/category/";
        } else {
            model.addAttribute("baseResult", delete);
            return "category_form";
        }
    }
}
