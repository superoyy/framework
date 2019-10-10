package com.dukla.base.util.excel;

/*
 * Excel数据验证消息
 * by Dukla.ou
 */
public class ExcelMessage {
	
	public final static int LEVEL_INFO=0;
	
	public final static int LEVEL_WARN=1;
	
	public final static int LEVEL_ERROR=2;
	
	private int level;
	
	private String message;
	
	public ExcelMessage(int level,String message){
		this.level = level;
		this.message = message;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
