package com.xupt.api.mapper;

import com.xupt.domain.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Mapper
public interface UserMapper {

    @Select("select id,username,password,email,updated, phone from tb_user where id = #{id}")
    User getUser();

    @Insert("insert into tb_user (username,password,phone,email,created,updated) values (#{user.username},#{user.password},#{user.phone},#{user.email},#{user.created},#{user.updated})")
    int insert(@Param("user") User user);

    @Select("select username,email,phone,password from tb_user where username = #{username}")
    User selectByUsername(String username);

    @Select("select count(*) from tb_user where email = #{email}")
    int getUserByEmail(String email);

    @Select("select count(*) from tb_user where username = #{username}")
    int getUserByName(String username);
}
