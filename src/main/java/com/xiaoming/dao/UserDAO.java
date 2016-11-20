package com.xiaoming.dao;

import com.xiaoming.dto.UserDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaoming on 17/11/2016.
 */
@Repository
public class UserDAO extends BaseDAO {
    public UserDTO find(UserDTO userDTO){
        return (UserDTO)getSqlMapClientTemplate().queryForObject("user.find", userDTO);
    }
}
