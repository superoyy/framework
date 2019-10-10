package com.timanetworks.iov.core.sql.excecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * SQL执行器抽象类
 * 欧阳亚
 * 2016.10
 */
public abstract class SqlExecutor{

    protected static final Logger logger = LoggerFactory.getLogger(SqlExecutor.class);

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /*
     * 集合结果解析器
     * 欧阳亚
     * 2009.12
     */
    public final class ListExtractor implements ResultSetExtractor {
        public Object extractData(ResultSet rs) throws SQLException,
                DataAccessException {
            int clmnum = rs.getMetaData().getColumnCount();
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            while (rs.next()) {
                Map<String,Object> map = new HashMap<String,Object>();
                for (int i = 1; i <= clmnum; i++) {
                    map.put(rs.getMetaData().getColumnLabel(i).toUpperCase(), rs.getObject(i));
                }
                list.add(map);
            }
            return list;
        }
    }

    /*
     * 记录结果解析器
     * 欧阳亚
     * 2009.12
     */
    public final class RecordExtractor implements ResultSetExtractor {
        public Object extractData(ResultSet rs) throws SQLException,
                DataAccessException {
            int clmnum = rs.getMetaData().getColumnCount();
            Map<String,Object> map = new HashMap<String,Object>();
            if (rs.next()) {
                for (int i = 1; i <= clmnum; i++) {
                    map.put(rs.getMetaData().getColumnLabel(i).toUpperCase(), rs.getObject(i));
                }
            }
            return map;
        }
    }

    /*
     * 单字段结果解析器
     * 欧阳亚
     * 2009.12
     */
    public final class FieldExtractor implements ResultSetExtractor {
        public Object extractData(ResultSet rs) throws SQLException,
                DataAccessException {
            Object value = null;
            if (rs.next()) {
                value = rs.getObject(1);
            }
            return value;
        }
    }
    /*
     * 存储过程执行器
     * 欧阳亚
     * 2009.12
     */
    public final class ProcCallableStatementCreator implements CallableStatementCreator {
        private String procStr;

        public ProcCallableStatementCreator(String procStr) {
            this.procStr = procStr;
        }

        public CallableStatement createCallableStatement(Connection conn) {
            String statement = "call "+this.procStr;
            CallableStatement cs = null;
            try {
                cs = conn.prepareCall(statement);
            } catch (SQLException e) {
                throw new RuntimeException("createCallableStatement method Error : SQLException " + e.getMessage());
            }
            return cs;
        }

    }
    /*
     * 取得参数列表串
     * 欧阳亚
     * 2009.12
     */
    protected String getSqlParamStr(Object[] args){
        if(args==null){
            return "param==null";
        }
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<args.length;i++){
            sb.append("Param["+i+"]="+(args[i] != null ? args[i].toString() : "")+",");
        }
        return sb.toString();
    }

    /*
     * 使用Statement得到一个值
     * 欧阳亚
     * 2009.12
     */
    public Object getOneValue(String sqlStr){
        logger.debug(sqlStr);
        return jdbcTemplate.query(sqlStr, new FieldExtractor());
    }

    /*
     * 使用PrepareStatement得到一个值
     * 欧阳亚
     * 2009.12
     */
    public Object getOneValue(String sqlStr, Object[] args){
        logger.debug(sqlStr+"\n"+getSqlParamStr(args));
        return jdbcTemplate.query(sqlStr, args, new FieldExtractor());
    }

    /*
     * 使用Statement得到一条记录
     * 欧阳亚
     * 2009.12
     */
    public Map<String,Object> getOneRecord(String sqlStr){
        logger.debug(sqlStr);
        return (Map<String,Object>) jdbcTemplate.query(sqlStr, new RecordExtractor());
    }

    /*
     * 使用PrepareStatement得到一条记录
     * 欧阳亚
     * 2009.12
     */
    public Map<String,Object> getOneRecord(String sqlStr, Object[] args){
        logger.debug(sqlStr+"\n"+getSqlParamStr(args));
        return (Map<String,Object>) jdbcTemplate.query(sqlStr, args,    new RecordExtractor());
    }
    /*
     * 使用Statement得到记录集合
     * 欧阳亚
     * 2009.12
     */
    public List<Map<String,Object>> getRecordsList(String sqlStr){
        logger.debug(sqlStr);
        return (List<Map<String,Object>>) jdbcTemplate.query(sqlStr, new ListExtractor());
    }
    /*
     * 使用Statement得到分页记录集合
     * 欧阳亚
     * 2009.12
     */
    public abstract List<Map<String,Object>> getRecordsList(String sqlStr,int start,int count);
    /*
     * 使用PrepareStatement得到记录集合
     * 欧阳亚
     * 2009.12
     */
    public List<Map<String,Object>> getRecordsList(String sqlStr, Object[] args){
        logger.debug(sqlStr+"\n"+getSqlParamStr(args));
        return (List<Map<String,Object>>) jdbcTemplate.query(sqlStr, args,new ListExtractor());
    }
    /*
     * 使用PrepareStatement得到分页记录集合
     * 欧阳亚
     * 2009.12
     */
    public abstract List<Map<String,Object>> getRecordsList(String sqlStr, Object[] args,int start,int count);
    /*
     * 使用Statement执行增、删、改操作
     * 欧阳亚
     * 2009.12
     */
    public int execute(String sqlStr){
        logger.debug(sqlStr);
        return jdbcTemplate.update(sqlStr);
    }
    /**
     * 批量执行sql
     * @param sqlStr
     */
    public int execute(List<String> sqlStr){
        int count = 0;
        for(String str:sqlStr)
            count +=this.execute(str);
        return count;
    }
    /**
     * 带参数的批量执行sql
     * @param sqlStr
     * @param parm
     */
    public int execute(List<String> sqlStr,List<Object[]> parm){
        int count = 0;
        for(int i=0;i<sqlStr.size();i++){
            count +=this.execute(sqlStr.get(i),parm.get(i));
        }
        return count;
    }
    /*
     * 使用PrepareStatement执行增、删、改操作
     * 欧阳亚
     * 2009.12
     */
    public int execute(String sqlStr, Object[] args){
        logger.debug(sqlStr+"\n"+getSqlParamStr(args));
        return jdbcTemplate.update(sqlStr,args);
    }

    /*
     * 执行存储过程
     * 欧阳亚
     * 2009.12
     */
    public Map<String, Object> callProcedure(String proc,List<SqlParameter> args){
        logger.debug("call:"+proc+"\n");
        return jdbcTemplate.call(new ProcCallableStatementCreator(proc), args);
    }


}
