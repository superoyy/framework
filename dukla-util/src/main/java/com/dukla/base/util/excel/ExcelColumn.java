package com.dukla.base.util.excel;

/*
 * Excel列定义
 * by Dukla.ou
 */
public class ExcelColumn {
	
	public final static int TYPE_STRING=0;
	
	public final static int TYPE_NUMBER=1;
	
	public final static int TYPE_DATE=2;
	
	//列号
	private int colNum;
	
	//列类型
	private int colType;
	
	//列名称
	private String colName;
	
	//最大长度
	private int length;
	
	//为空标识
	private boolean empty;
	
	//验证信息
	private String verifyMsg;
	
	//匹配正则表达式
	private String regularExp;
	
	public ExcelColumn(){
		
	}
	
	//构造函数
	public ExcelColumn(int colNum,String colName,int colType,int length,boolean empty,String regularExp,String verifyMsg){
		this.colNum = colNum;
		this.colName = colName;
		this.colType = colType;
		this.length = length;
		this.empty = empty;
		this.verifyMsg = verifyMsg;
		this.regularExp = regularExp;
		
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public int getColType() {
		return colType;
	}

	public void setColType(int colType) {
		this.colType = colType;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public String getVerifyMsg() {
		return verifyMsg;
	}

	public void setVerifyMsg(String verifyMsg) {
		this.verifyMsg = verifyMsg;
	}

	public String getRegularExp() {
		return regularExp;
	}

	public void setRegularExp(String regularExp) {
		this.regularExp = regularExp;
	}
	
}
