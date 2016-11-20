package com.xiaoming.Servlet;

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
    //private ThreadLocal<UserDTO> userDTOThreadLocal = new ThreadLocal<UserDTO>();

//    @Override
//    public void fillContext(Context ctx, HttpServletRequest request) {
//        if ("/login.vm".equals(request.getServletPath())) {
//
//        }else if("/page_404.jsp".equals(request.getServletPath())){
//
//        }else{
//            UserDTO userDTO = userDTOThreadLocal.get();
//            super.getServletContext();
//            UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
//            List<MenuDTO> list = userService.getAccessMenus(userDTO.getId());
//            ctx.put("menuList", list);
//            ctx.put("name", userDTO.getName());
//        }
//
//        super.fillContext(ctx, request);
//    }
//
//    @Override
//    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        if ("/login.vm".equals(request.getServletPath())) {
//
//        }else if("/page_404.jsp".equals(request.getServletPath())){
//
//        }else{
//            UserDTO userDTO = CommonUtils.getUserFromReqToken(request);
//            userDTOThreadLocal.set(userDTO);
//            if (userDTO == null) {
//                response.sendRedirect("/login.vm");
//                return;
//            }
//        }
//        super.doRequest(request, response);
//    }

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
                }
            }
            UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
            List<MenuDTO> list = userService.getAccessMenus(userDTO.getId());
            ctx.put("menuList", list);
            ctx.put("name", userDTO.getName());
        }
        return this.getTemplate(request, response);
    }
}
