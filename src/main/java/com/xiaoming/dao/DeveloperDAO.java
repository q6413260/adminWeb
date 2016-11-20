package com.xiaoming.dao;

import com.xiaoming.dto.DeveloperDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoming on 16/11/2016.
 */
@Repository
public class DeveloperDAO extends BaseDAO {

    public List<DeveloperDTO> find(DeveloperDTO developerDTO){
        return getSqlMapClientTemplate().queryForList("developer.find", developerDTO);
    }

    public Long insert(DeveloperDTO developerDTO){
        return (Long)getSqlMapClientTemplate().insert("developer.insert", developerDTO);
    }
}
