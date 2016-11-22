package com.xiaoming.dao;

import com.xiaoming.dto.ApiAttributeDTO;
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

    public void inert(ApiDTO apiDTO){
        getSqlMapClientTemplate().insert("api.insert", apiDTO);
    }

    public List<ApiAttributeDTO> queryAttrByApiId(Long apiId) {
        return getSqlMapClientTemplate().queryForList("api.findAttrByApiId", apiId);
    }

    public void insertAttribute(ApiAttributeDTO apiAttributeDTO) {
        getSqlMapClientTemplate().insert("api.insertAttribute", apiAttributeDTO);
    }
}
