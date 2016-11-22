package com.xiaoming.controller;

import com.xiaoming.dto.UserDTO;
import com.xiaoming.service.UserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaoming on 16/11/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("redirect:/login.vm");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            mav.addObject("errorMsg", "账号/密码不能为空");
            return mav;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setPassword(password);
        String token = userService.authenticate(userDTO);
        if(StringUtils.isEmpty(token)){
            mav.addObject("errorMsg", "账号或者密码错误");
            return mav;
        }
        mav = new ModelAndView("redirect:/developer.vm");
        Cookie cookie =new Cookie("token", token);
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
        return mav;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("redirect:/login.vm");
        Cookie[] cookies = request.getCookies();
        if(!ArrayUtils.isEmpty(cookies)){
            for(Cookie cookie : cookies){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return mav;
    }
}
