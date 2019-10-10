package com.timanetworks.iov.core.sql.excecutor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
 * SQL执行器MySql扩展
 * 欧阳亚
 * 2016.10
 */
@Component("sqlExecutor")
public class SqlExecutorMySqlImp extends SqlExecutor {

    @Override
    public List<Map<String, Object>> getRecordsList(String sqlStr, int start,int count) {
        StringBuilder sb=new StringBuilder();
        sb.append("select * from (");
        sb.append(sqlStr);
        sb.append(") t limit "+String.valueOf(start)+","+String.valueOf(count));
        sqlStr=sb.toString();
        logger.debug(sqlStr);
        return (List<Map<String,Object>>) jdbcTemplate.query(sqlStr, new ListExtractor());
    }

    @Override
    public List<Map<String, Object>> getRecordsList(String sqlStr,Object[] args, int start, int count) {
        StringBuilder sb=new StringBuilder();
        sb.append("select * from (");
        sb.append(sqlStr);
        sb.append(") t limit "+String.valueOf(start)+","+String.valueOf(count));
        sqlStr=sb.toString();
        logger.debug(sqlStr+"\n"+getSqlParamStr(args));
        return (List<Map<String,Object>>) jdbcTemplate.query(sqlStr, args, new ListExtractor());
    }

}
