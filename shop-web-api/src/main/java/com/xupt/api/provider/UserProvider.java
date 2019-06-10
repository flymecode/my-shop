package com.xupt.api.provider;

import com.xupt.domain.user.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author maxu
 * @date 2019/6/10
 */
public class UserProvider {
    public String login(final User user) {
        return new SQL() {{
            SELECT("*");
            FROM("tb_user");
            if (StringUtils.isNotEmpty(user.getUsername())) {
                WHERE("username = '" + user.getUsername()+"'");
            }
            if (StringUtils.isNotEmpty(user.getPhone())) {
                WHERE("phone = '" + user.getPhone() + "'");
            }
        }}.toString();
    }
}
