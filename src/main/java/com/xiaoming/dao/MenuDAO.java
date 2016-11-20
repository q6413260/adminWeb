package com.xiaoming.dao;

import com.xiaoming.dto.MenuDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoming on 17/11/2016.
 */
@Repository
public class MenuDAO extends BaseDAO{
    public List<MenuDTO> findByIdList(List<Long> list){
        return getSqlMapClientTemplate().queryForList("menu.findByIdList", list);
    }
}

