package com.dukla.base.util.excel;

import java.util.List;

/*
 * Excel配置
 * by Dukla.ou
 */
public class ExcelConfig {
	
	public final static int OFFICE_2003=0;
	
	public final static int OFFICE_2007=1;
	
	//文件限制文件大小
	private long fileSize;
	
	//Excel版本
	private int officeVer;
	
	//解析开始行
	private int startRowNum;
	
	//解析结束行
	private int endRowNum;
	
	//列定义
	private List<ExcelColumn> excelColumnList;

	//验证文件路径
	private String verifyFilePath;

	//验证文件名
	private String verifyFileName;

	//写文件路径
	private String writeFilePath;

	//写文件名
	private String writeFileName;


	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getOfficeVer() {
		return officeVer;
	}

	public void setOfficeVer(int officeVer) {
		this.officeVer = officeVer;
	}

	public String getVerifyFileName() {
		return verifyFileName;
	}

	public void setVerifyFileName(String verifyFileName) {
		this.verifyFileName = verifyFileName;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public List<ExcelColumn> getExcelColumnList() {
		return excelColumnList;
	}

	public void setExcelColumnList(List<ExcelColumn> excelColumnList) {
		this.excelColumnList = excelColumnList;
	}

	public String getVerifyFilePath() {
		return verifyFilePath;
	}

	public void setVerifyFilePath(String verifyFilePath) {
		this.verifyFilePath = verifyFilePath;
	}

	public String getWriteFilePath() {
		return writeFilePath;
	}

	public void setWriteFilePath(String writeFilePath) {
		this.writeFilePath = writeFilePath;
	}

	public String getWriteFileName() {
		return writeFileName;
	}

	public void setWriteFileName(String writeFileName) {
		this.writeFileName = writeFileName;
	}
	
}
