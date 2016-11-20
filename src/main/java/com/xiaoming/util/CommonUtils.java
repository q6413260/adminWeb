package com.xiaoming.util;

import com.xiaoming.dto.UserDTO;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaoming on 19/11/2016.
 */
public class CommonUtils {
    private final static JedisClientManager jedis = new JedisClientManager();

    public static UserDTO getUserFromReqToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        if(StringUtils.isEmpty(token)){
            return null;
        }
        UserDTO userDTO = (UserDTO) jedis.getObject(token, UserDTO.class);
        return userDTO;
    }

}
