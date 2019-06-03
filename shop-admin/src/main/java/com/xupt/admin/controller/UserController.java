package com.xupt.admin.controller;

import com.xupt.admin.service.UserService;
import com.xupt.admin.validator.UserForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String saveUser(@Valid UserForm user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ResultMap resultMap = new ResultMap().fail().message(bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("baseResult", resultMap);
            return "user_list";
        }
        ResultMap resultMap = userService.saveUser(user);
        model.addAttribute("baseResult", resultMap);
        return "user_list";
    }

    @GetMapping("/{id}/edit")
    public String getUser(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
        User user = userService.getUser(id);
        log.info(user.toString());
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/user/form";
    }
    @ResponseBody
    @GetMapping("/{id}/detail")
    public User getUserDetail(@PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        return user;
    }
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ResultMap success  = userService.updateUser(id);
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
        ResultMap resultMap = userService.deleteUsers(ids);
        return resultMap;
    }
    @ResponseBody
    @GetMapping("/page")
    public Map<String,Object> listUsersPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                             @RequestParam(value = "length",defaultValue = "10") Integer length,
                             @RequestParam(value = "draw",defaultValue = "1") Integer draw) {
        ResultMap resultMap = userService.listUsers(start, length, draw);

        return resultMap;
    }
}
