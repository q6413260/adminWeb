package com.xiaoming.service;

import com.xiaoming.dao.DeveloperDAO;
import com.xiaoming.dto.DeveloperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaoming on 16/11/2016.
 */
@Service
public class DeveloperService {
    @Autowired
    private DeveloperDAO developerDAO;

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
}
