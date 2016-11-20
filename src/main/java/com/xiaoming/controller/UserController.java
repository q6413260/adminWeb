package com.xiaoming.controller;

import com.xiaoming.dto.MenuDTO;
import com.xiaoming.dto.UserDTO;
import com.xiaoming.service.UserService;
import com.xiaoming.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xiaoming on 17/11/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/menu/query", method = RequestMethod.GET)
    public @ResponseBody List<MenuDTO> queryResource(HttpServletRequest request){
        UserDTO userDTO = (UserDTO)request.getAttribute(Constants.USER_REQ_ATTRIBUTE_KEY);
        Long userId = userDTO.getId();
        List<MenuDTO> list = userService.getAccessMenus(userId);
        return list;
    }
}
