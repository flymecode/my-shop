package com.xupt.admin.controller;

import com.github.pagehelper.PageInfo;
import com.xupt.admin.service.UserService;
import com.xupt.common.dto.ResultMap;
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
 * @date 2019/5/29
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String saveUser(User user, Model model) {
        ResultMap fail = ResultMap.fail();
        model.addAttribute("baseResult", fail);
        return "user_list";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
        userService.updateUser(id);
        ResultMap success = ResultMap.success();
        redirectAttributes.addFlashAttribute("baseResult", success);
        return "redirect:/user/list";
    }

    @PostMapping("/search")
    public String searchUsers() {
        log.info("来了");
        return "user_list";
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResultMap deleteUsers(@RequestParam("ids") String ids) {
        log.info(ids);
        ResultMap success = ResultMap.fail();
        userService.deleteUsers(ids);
        return success;
    }
    @ResponseBody
    @GetMapping("/page")
    public Map<String,Object> listUsersPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                             @RequestParam(value = "length",defaultValue = "10") Integer length,
                             @RequestParam(value = "draw",defaultValue = "1") Integer draw) {
        PageInfo<User> users = userService.listUsers(start, length);
        Map<String, Object> result = new HashMap<>();
        result.put("draw",draw);
        result.put("recordsTotal",users.getTotal());
        result.put("recordsFiltered",users.getTotal());
        result.put("data",users.getList());
        result.put("error","");
        return result;
    }
}
