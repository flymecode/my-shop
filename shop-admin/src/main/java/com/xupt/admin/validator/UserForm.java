package com.xupt.admin.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author maxu
 * @date 2019/6/2
 */
@Data
public class UserForm {
    @Length(min = 6, max = 20, message = "姓名的长度必须介于 6-20 位")
    private String username;
    private String email;
    private String phone;
}
