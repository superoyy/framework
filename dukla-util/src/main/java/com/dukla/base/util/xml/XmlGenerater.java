package com.dukla.base.util.xml;

import java.util.List;

public interface XmlGenerater {
	/*
	 * 根据实体对象产生xml
	 */
	public String createXml(Object entity)throws Exception;
	/*
	 * 根据实体对象和xslt文件产生对象的html
	 */
	public String createHtml(Object entity, String xsltPath)throws Exception;
	/*
	 * 根据实体对象列表产生xml
	 */
	public String createXml(List<Object> entityList, String pageHtml, int pageNum, int pageSize) throws Exception;
	/*
	 * 根据实体对象列表和xslt文件产生列表html
	 */
	public String createHtml(List<Object> entityList, String pageHtml, int pageNum, int pageSize, String xsltPath) throws Exception;
}
