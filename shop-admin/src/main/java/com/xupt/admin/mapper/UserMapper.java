package com.xupt.admin.mapper;

import com.xupt.admin.mapper.provider.UserProvicer;
import com.xupt.admin.validator.UserForm;
import com.xupt.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Mapper
public interface UserMapper {

    @Insert("insert into tb_user (username,password,phone,email,created,updated) values (#{user.username},#{user.password},#{user.phone},#{user.email},#{user.created},#{user.updated})")
    void insertUser(@Param("user") User user);

    void updateUser(User user);

    @SelectProvider(type = UserProvicer.class, method = "serachUser")
    List<User> searchUsers(UserForm user);

    @SelectProvider(type = UserProvicer.class, method = "deleteUsers")
    void deleteUsers(String id);

    @Select("select id,username,password,email,updated, phone from tb_user where id = #{id}")
    User getUser(@Param("id") Integer id);

}
