package com.xupt.admin.service;

import com.github.pagehelper.PageInfo;
import com.xupt.domain.User;

/**
 * user service
 * @author maxu
 * @date 2019/5/29
 */
public interface UserService {
    void saveUser(User user);

    PageInfo<User> listUsers(Integer page, Integer count);

    void deleteUsers(String ids);

    void updateUser(Integer id);

    User getUser(Integer id);
}
