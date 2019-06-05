package com.xupt.ui.api;

import com.xupt.common.untils.HttpClientUtils;
import com.xupt.common.untils.MapperUtils;
import com.xupt.domain.User;
import com.xupt.ui.vo.UserLoginVO;
import com.xupt.ui.vo.UserRegisterVO;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/5
 */
public class UserAPI {

    public static User login(UserLoginVO user) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", user.getUsername()));
        params.add(new BasicNameValuePair("password", user.getPassword()));
        String result = HttpClientUtils.doPost(API.API_USER_LOGIN,params.toArray(new BasicNameValuePair[params.size()]));
        return MapperUtils.json2pojo(result, User.class);
    }


    public static User register(UserRegisterVO user) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", user.getUsername()));
        params.add(new BasicNameValuePair("password", user.getPassword()));
        params.add(new BasicNameValuePair("email", user.getEmail()));
        params.add(new BasicNameValuePair("phone", user.getPhone()));
        String result = HttpClientUtils.doPost(API.API_USER_REGISTER,params.toArray(new BasicNameValuePair[params.size()]));
        System.out.println(result);
        return MapperUtils.json2pojo(result, User.class);
    }
}
