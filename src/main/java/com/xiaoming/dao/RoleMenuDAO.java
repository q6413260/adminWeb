package com.xiaoming.dao;

import com.xiaoming.dto.RoleMenuDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoming on 17/11/2016.
 */
@Repository
public class RoleMenuDAO extends BaseDAO{
    public List<RoleMenuDTO> findMenuByRoleId(Long roleId){
        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
        roleMenuDTO.setRoleId(roleId);
        return getSqlMapClientTemplate().queryForList("roleMenu.find", roleMenuDTO);
    }
}
