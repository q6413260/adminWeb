package com.xiaoming.service;

import com.xiaoming.dao.ApiDAO;
import com.xiaoming.dao.DeveloperDAO;
import com.xiaoming.dto.ApiDTO;
import com.xiaoming.dto.DeveloperApiDTO;
import com.xiaoming.dto.DeveloperDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaoming on 16/11/2016.
 */
@Service
public class DeveloperService {
    @Autowired
    private DeveloperDAO developerDAO;
    @Autowired
    private ApiDAO       apiDAO;

    public List<DeveloperDTO> find(DeveloperDTO developerDTO) {
        return developerDAO.find(developerDTO);
    }

    public void insert(DeveloperDTO developerDTO) {
        developerDTO.setGmtCreated(new Date());
        developerDTO.setGmtModified(new Date());
        developerDTO.setRsaLength(1024);
        developerDTO.setStatus("NORMAL");
        developerDAO.insert(developerDTO);
    }

    public List<DeveloperApiDTO> queryAccessedApi(Long developerId) {
        List<DeveloperApiDTO> list = developerDAO.findAccessedApi(developerId);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<DeveloperApiDTO>();
        }
        for (DeveloperApiDTO developerApiDTO : list) {
            ApiDTO condition = new ApiDTO();
            condition.setId(developerApiDTO.getApiId());
            List<ApiDTO> apiDTOList = apiDAO.query(condition);
            ApiDTO apiDTO = apiDTOList.get(0);
            developerApiDTO.setApiServiceName(apiDTO.getServiceName());
            developerApiDTO.setApiDescription(apiDTO.getDescription());
        }
        return list;
    }

    public void insertDeveloperApi(DeveloperApiDTO developerApiDTO){
        developerDAO.insertDevApi(developerApiDTO);
    }
}
