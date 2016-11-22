package com.xiaoming.service;

import com.xiaoming.dao.ApiDAO;
import com.xiaoming.dto.ApiAttributeDTO;
import com.xiaoming.dto.ApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaoming on 21/11/2016.
 */
@Service
public class ApiService {
    @Autowired
    private ApiDAO apiDAO;

    public List<ApiDTO> queryApi(ApiDTO apiDTO) {
        List<ApiDTO> list = apiDAO.query(apiDTO);
        return list;
    }

    public List<ApiAttributeDTO> queryAttrByApiId(Long apiId){
        List<ApiAttributeDTO> list = apiDAO.queryAttrByApiId(apiId);
        return list;
    }

    public void insert(ApiDTO apiDTO){
        apiDTO.setGmtCreated(new Date());
        apiDTO.setGmtModified(new Date());
        apiDAO.inert(apiDTO);
    }

    public void addAttribute(ApiAttributeDTO apiAttributeDTO) {
        apiDAO.insertAttribute(apiAttributeDTO);
    }
}
