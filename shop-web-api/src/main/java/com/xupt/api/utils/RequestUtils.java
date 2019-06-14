package com.xupt.api.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author maxu
 * @date 2019/6/12
 */
public class RequestUtils {

    //获取CSessionID
    public static String getCSessionId(HttpServletRequest request) {
        //1, 从Request中取Cookie
        Cookie[] cookies = request.getCookies();
        //2, 从Cookie数据中遍历查找, 并取CSessionID
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("CSESSIONID".equals(cookie.getName())) {
                    //有, 直接返回
                    return cookie.getValue();
                }
            }
        }
        //没有, 创建一个CSessionId, 并且放到Cookie再返回浏览器.返回新的CSessionID
        String csessionid = UUID.randomUUID().toString().replaceAll("-", "");
        //并且放到Cookie中
        Cookie cookie = new Cookie("CSESSIONID", csessionid);
        //cookie  每次都带来, 设置路径
        cookie.setPath("/");
        //0:关闭浏览器  销毁cookie. 0:立即消失.  >0 存活时间,秒
        cookie.setMaxAge(-1);
        return csessionid;
    }


}
