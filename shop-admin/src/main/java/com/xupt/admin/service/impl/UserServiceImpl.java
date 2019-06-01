package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.UserMapper;
import com.xupt.admin.service.UserService;
import com.xupt.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public void saveUser(User user) {
        // TODO 这里密码没有进行加密
        user.setUpdated(new Date());
        if (user.getId() == null) {
            userMapper.insertUser(user);
        } else {
            userMapper.updateUser(user);
        }
    }

    @Override
    public  PageInfo<User>  listUsers(Integer page, Integer count) {
        PageHelper.offsetPage(page, count);
        List<User> users = userMapper.searchUsers();
        PageInfo<User> pages = PageInfo.of(users);
        if (users != null) {
            return pages;
        } else {
            throw new RuntimeException("异常");
        }
    }

    @Override
    public void deleteUsers(String ids) {
        userMapper.deleteUsers(ids);
    }

    @Override
    public void updateUser(Integer id) {

    }

    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }
}
