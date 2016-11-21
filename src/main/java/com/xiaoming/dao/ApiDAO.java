package com.xiaoming.dao;

import com.xiaoming.dto.ApiDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoming on 21/11/2016.
 */
@Repository
public class ApiDAO extends BaseDAO{
    public List<ApiDTO> query(ApiDTO apiDTO){
        return getSqlMapClientTemplate().queryForList("api.find", apiDTO);
    }
}
