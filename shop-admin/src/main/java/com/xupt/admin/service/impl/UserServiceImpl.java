package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.UserMapper;
import com.xupt.admin.service.UserService;
import com.xupt.admin.validator.UserForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public ResultMap saveUser(User user) {
        Date date = new Date();
        user.setUpdated(date);
        if (user.getId() == null) {
            user.setCreated(date);
            userMapper.insertUser(user);
        } else {
            userMapper.updateUser(user);
        }
        return null;
    }

    @Override
    public ResultMap  listUsers(Integer page, Integer count, Integer draw, UserForm userForm) {
        ResultMap result = new ResultMap();
        PageHelper.offsetPage(page, count);
        List<User> users = userMapper.searchUsers(userForm);
        log.info(users.toString());
        PageInfo<User> pages = PageInfo.of(users);
        result.payload(pages.getList());
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotal());
        result.put("recordsFiltered",pages.getTotal());
        result.put("error","");
        return result;
    }

    @Override
    public ResultMap deleteUsers(String ids) {
        userMapper.deleteUsers(ids);
        return null;
    }

    @Override
    public ResultMap updateUser(Integer id) {
        ResultMap resultMap = new ResultMap();
        return resultMap;
    }

    @Override
    public ResultMap getUser(Integer id) {
        ResultMap resultMap = new ResultMap();
        User user = userMapper.getUser(id);
        return resultMap.payload(user);
    }
}
