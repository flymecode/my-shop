package com.xupt.admin.mapper.provider;

import com.xupt.admin.validator.UserForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author maxu
 * @date 2019/5/30
 */
public class UserProvicer {

    public String serachUser(final UserForm user) {
       return new SQL(){{
            SELECT("id,username,phone,email,updated");
            FROM("tb_user");
            if (StringUtils.isNotEmpty(user.getUsername())) {
                WHERE("username like '" + user.getUsername() + "%'");
            }
            if (StringUtils.isNotEmpty(user.getEmail())) {
                WHERE("email like '" + user.getEmail() + "%'");
            }
            if (StringUtils.isNotEmpty(user.getPhone())) {
                WHERE("phone like '" + user.getPhone() + "%'");
            }
        }}.toString();
    }

    public String deleteUsers(final String ids) {
        return new SQL() {{
            DELETE_FROM("tb_user");
            WHERE("id in ("+ids+")");
        }}.toString();
    }
}
