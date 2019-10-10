package com.dukla.base.sql.handler;

import com.dukla.base.sql.excecutor.SqlExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 执行SQL语句的服务实现
 * @version 1.0
 * @author 欧阳亚
 * @since 2011.11.23
 */
@Service("sqlService")
public class SqlHandlerImpl implements SqlHandler {

    @Autowired
	private SqlExecutor sqlExecutor;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public Map<String, Object> getOneRecord(String sqlStr) {
		return this.sqlExecutor.getOneRecord(sqlStr);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public Map<String, Object> getOneRecord(String sqlStr, Object[] args) {
		return this.sqlExecutor.getOneRecord(sqlStr, args);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public Object getOneValue(String sqlStr) {
		return this.sqlExecutor.getOneValue(sqlStr);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public Object getOneValue(String sqlStr, Object[] args) {
		return this.sqlExecutor.getOneValue(sqlStr, args);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public List<Map<String, Object>> getRecordsList(String sqlStr) {
		return this.sqlExecutor.getRecordsList(sqlStr);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public List<Map<String, Object>> getRecordsList(String sqlStr, int start,int count) {
		return this.sqlExecutor.getRecordsList(sqlStr, start, count);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public List<Map<String, Object>> getRecordsList(String sqlStr, Object[] args) {
		return this.sqlExecutor.getRecordsList(sqlStr, args);
	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
	public List<Map<String, Object>> getRecordsList(String sqlStr,Object[] args, int start, int count) {
		return this.sqlExecutor.getRecordsList(sqlStr, args, start, count);
	}

    @Transactional
    @Override
	public int execute(String sqlStr) {
		return this.sqlExecutor.execute(sqlStr);
	}

    @Transactional
    @Override
	public int execute(String sqlStr, Object[] args) {
		return this.sqlExecutor.execute(sqlStr,args);
	}

    @Transactional
    @Override
	public int executeBatch(List<String> sqlStr) {
		return this.sqlExecutor.execute(sqlStr);
	}

    @Transactional
    @Override
	public int executeBatch(List<String> sqlStr, List<Object[]> param) {
		return this.sqlExecutor.execute(sqlStr, param);

	}

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String, Object> callProcedure(String proc, List<SqlParameter> args) {
		return this.sqlExecutor.callProcedure(proc, args);
	}

}
