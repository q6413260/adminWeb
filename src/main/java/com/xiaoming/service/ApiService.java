package com.xiaoming.service;

import com.xiaoming.dao.ApiDAO;
import com.xiaoming.dto.ApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
