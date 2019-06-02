package com.xupt.admin.mapper;

import com.xupt.admin.mapper.provider.UserProvicer;
import com.xupt.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Mapper
public interface UserMapper {
    void insertUser(User user);

    void updateUser(User user);

    @SelectProvider(type = UserProvicer.class,method = "serachUser")
    List<User> searchUsers();

    @SelectProvider(type = UserProvicer.class,method = "deleteUsers")
    void deleteUsers(String id);

    @Select("select id,username,password,email,phone from tb_user where id = #{id}")
    User getUser(@Param("id") Integer id);

}
