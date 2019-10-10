package com.timanetworks.iov.util.json;

public interface JsonGenerater {
	
	//不用引号的 key-value JSON 
	public static final int KEY_VALUE_STYLE_NO_QUOT = 0;

	//用引号的 key-value JSON 
	public static final int KEY_VALUE_STYLE_QUOT = 1;
	
	/**
	 * 根据实体对象产生Json
	 * keyValueStyle:键值对样式
	 * 0:普通样式{key:'value'}
	 * 1:用引号包裹形如{"key":"value"}
	 */
	public String createJson(Object entity, int keyValueStyle) throws Exception;

}
