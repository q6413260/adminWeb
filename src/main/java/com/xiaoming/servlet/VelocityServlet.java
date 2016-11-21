package com.xiaoming.servlet;

import com.xiaoming.dto.MenuDTO;
import com.xiaoming.dto.UserDTO;
import com.xiaoming.service.UserService;
import com.xiaoming.util.ApplicationContextUtil;
import com.xiaoming.util.CommonUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityLayoutServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiaoming on 19/11/2016.
 */
public class VelocityServlet extends VelocityLayoutServlet {
    @Override
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
        if ("/login.vm".equals(request.getServletPath())) {

        }else{
            UserDTO userDTO = CommonUtils.getUserFromReqToken(request);
            if (userDTO == null) {
                try {
                    response.sendRedirect("/login.vm");
                    return this.getTemplate(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            UserService userService = ApplicationContextUtil.getBean("userService");
            List<MenuDTO> list = userService.getAccessMenus(userDTO.getId());
            ctx.put("menuList", list);
            ctx.put("name", userDTO.getName());
        }
        return this.getTemplate(request, response);
    }
}
