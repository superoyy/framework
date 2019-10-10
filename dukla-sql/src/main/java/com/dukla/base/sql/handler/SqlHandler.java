package com.dukla.base.sql.handler;

import org.springframework.jdbc.core.SqlParameter;

import java.util.List;
import java.util.Map;

/*
 * 执行SQL语句的服务接口
 * 欧阳亚
 * 2016.10
 */
public interface SqlHandler {
	
	public Object getOneValue(String sqlStr);

	public Object getOneValue(String sqlStr, Object[] args);

	public Map<String, Object> getOneRecord(String sqlStr);

	public Map<String, Object> getOneRecord(String sqlStr, Object[] args);

	public List<Map<String, Object>> getRecordsList(String sqlStr);

	public List<Map<String, Object>> getRecordsList(String sqlStr, int start, int count);

	public List<Map<String, Object>> getRecordsList(String sqlStr, Object[] args);

	public List<Map<String, Object>> getRecordsList(String sqlStr, Object[] args, int start, int count);

	public int execute(String sqlStr);

	public int execute(String sqlStr, Object[] args);

	public int executeBatch(List<String> sqlStr);

	public int executeBatch(List<String> sqlStr, List<Object[]> param);

	public Map<String, Object> callProcedure(String proc, List<SqlParameter> args);
}
