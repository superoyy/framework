package com.dukla.base.jpa.dao;

import java.io.Serializable;

/*
 * 查询条件对象
 */
public class QueryParam implements Serializable{

	/*
	 * 条件关系 
	 * and | or
	 */
	private String conOper;

	/*
	 * 条件名称
	 */
	private String paramName;
	
	/*
	 * 操作符 
	 * 支持：=,like,<,<=,>,>=,<>,in,is,between    
	 */
	private String operator;
	
	/*
	 * 参数值
	 * 当operator为 =,like,<,<=,>,>= 时传单值 Object类型
	 * 当operator为 is 时只能为 "null" 和 "not null" 2种
	 * 当operator为 in/not in 时传Object[]类型，形如：new Object[]{1,2,3}
	 * 当operator为 between 时传Object[]类型，length=2 只取前2个值,形如：new Object[]{1,2}
	 */
	private Object paramValue;
	
	public QueryParam(String conOper, String paramName, String operator, Object paramValue){
		this.conOper=conOper;
		this.operator=operator;
		this.paramName=paramName;
		this.paramValue=paramValue;
	}
	

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getConOper() {
		return conOper;
	}

	public void setConOper(String conOper) {
		this.conOper = conOper;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}

}
