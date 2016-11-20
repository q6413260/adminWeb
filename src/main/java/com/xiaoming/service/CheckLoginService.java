package com.xiaoming.service;

import com.xiaoming.dto.UserDTO;
import com.xiaoming.util.CommonUtils;
import com.xiaoming.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaoming on 17/11/2016.
 */
@Service
public class CheckLoginService extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO userDTO = CommonUtils.getUserFromReqToken(request);
        request.setAttribute(Constants.USER_REQ_ATTRIBUTE_KEY, userDTO);
        return true;
    }
}
