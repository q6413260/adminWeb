package com.xiaoming.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by xiaoming on 16/11/2016.
 */
public class BaseDAO extends SqlMapClientDaoSupport {
    @Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;

    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }
}
