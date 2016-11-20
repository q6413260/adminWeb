package com.xiaoming.service;

import com.xiaoming.dao.MenuDAO;
import com.xiaoming.dao.RoleMenuDAO;
import com.xiaoming.dao.UserDAO;
import com.xiaoming.dto.MenuDTO;
import com.xiaoming.dto.RoleMenuDTO;
import com.xiaoming.dto.UserDTO;
import com.xiaoming.util.JedisClientManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.xiaoming.util.Constants.USER_TOKEN_PREFIX;

/**
 * Created by xiaoming on 17/11/2016.
 */
@Service
public class UserService {
    @Autowired
    private UserDAO     userDAO;

    @Autowired
    private RoleMenuDAO roleMenuDAO;

    @Autowired
    private MenuDAO     menuDAO;

    /**
     * Authenticate token dto.
     *
     * @param condition the user dto
     * @return the token dto
     */
    public String authenticate(UserDTO condition) {
        String token = "";
        UserDTO userDTO = userDAO.find(condition);
        if (userDTO == null) {
            return token;
        }
        JedisClientManager jedis = new JedisClientManager();
        token = USER_TOKEN_PREFIX + UUID.randomUUID().toString();
        jedis.putObject(token, userDTO);
        return token;
    }

    public List<MenuDTO> getAccessMenus(Long userId) {
        List<RoleMenuDTO> roleMenuDTOList = roleMenuDAO.findMenuByRoleId(userId);
        List<Long> menuIdList = new ArrayList<Long>();
        if (CollectionUtils.isEmpty(roleMenuDTOList)) {
            return new ArrayList<MenuDTO>();
        }
        for (RoleMenuDTO roleMenuDTO : roleMenuDTOList) {
            menuIdList.add(roleMenuDTO.getMenuId());
        }
        List<MenuDTO> menuList = menuDAO.findByIdList(menuIdList);
        return sortMenusByLevel(menuList);
    }

    private List<MenuDTO> sortMenusByLevel(List<MenuDTO> menuList) {
        List<MenuDTO> resultList = new ArrayList<MenuDTO>();
        Map<Long, List<MenuDTO>> map = new HashMap<Long, List<MenuDTO>>();
        for(MenuDTO menu : menuList){
            if(menu.getLevel() == 1){
                List<MenuDTO> childRen = new ArrayList<MenuDTO>();
                map.put(menu.getId(), childRen);
                menu.setChildren(childRen);
                resultList.add(menu);
            }else{
                List<MenuDTO>  childRen = map.get(menu.getParentId());
                childRen.add(menu);
            }
        }
        return resultList;
    }
}
