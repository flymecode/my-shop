package com.xupt.admin.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author maxu
 * @date 2019/5/30
 */
public class UserProvicer {

    public String serachUser() {
        return new SQL(){{
            SELECT("id,username,phone,email,updated");
            FROM("tb_user");
        }}.toString();
    }

    public String deleteUsers(final String ids) {
        return new SQL() {{
            DELETE_FROM("tb_user");
            WHERE("id in ("+ids+")");
        }}.toString();
    }
}
