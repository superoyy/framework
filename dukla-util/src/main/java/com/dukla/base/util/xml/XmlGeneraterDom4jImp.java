package com.dukla.base.util.xml;

import com.dukla.base.util.Kit;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class XmlGeneraterDom4jImp implements XmlGenerater,Serializable{
	/*
	 * 生成单个实体对象的xml
	 */
	public String createXml(Object entity) throws Exception {
		return this.createDocument(entity).asXML();
	}
	/*
	 * 生成单个实体对象的Html
	 */
	public String createHtml(Object entity,String xslt) throws Exception {
		Document document = this.createDocument(entity);
		return this.transXml(document, xslt);
	}
	/*
	 * 生成实体对象List的xml entityList：实体集合 pageHtml：分页控制html,如果没有传null pageNum:页数
	 * pageSize：每页显示行数
	 */
	@SuppressWarnings("rawtypes")
	public String createXml(List entityList, String pageHtml, int pageNum,
			int pageSize) throws Exception {
		return this.createDocument(entityList, pageHtml, pageNum, pageSize).asXML();
	}
	/*
	 * 生成实体对象List的Html entityList：实体集合 pageHtml：分页控制html,如果没有传null pageNum:页数
	 * pageSize：每页显示行数
	 * xstl解析器
	 */
	@SuppressWarnings("rawtypes")
	public String createHtml(List entityList, String pageHtml, int pageNum,
			int pageSize,String xslt) throws Exception {
		Document document =this.createDocument(entityList, pageHtml, pageNum, pageSize);
		return this.transXml(document, xslt);
	}
	/*
	 * 根据xslt转换xml
	 */
	private String transXml(Document document,String xslt) throws Exception {
		TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslt));
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
        //ProcessingInstruction pi = new FlyweightProcessingInstruction(DocumentResult.PI_DISABLE_OUTPUT_ESCAPING, "data");
        transformer.transform(source,result);
        Document transformedDoc = result.getDocument();
        return transformedDoc.asXML();
	}
	/*
	 * 生成节点xml文档对象(List类型)
	 */
	@SuppressWarnings("rawtypes")
	private Document createDocument(List entityList, String pageHtml, int pageNum,
			int pageSize) throws Exception {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		if (entityList != null && entityList.size() != 0) {
			Object entity = entityList.get(0);
			Class entityClass = entity.getClass();
			Element rootElement = document.addElement(entityClass.getSimpleName()+"List");
			Element pageElement = rootElement.addElement("PageHtml");
			pageElement.addCDATA((pageHtml != null ? pageHtml : ""));
			for (int i = 0; i < entityList.size(); i++) {
				entity = entityList.get(i);
				Element element = rootElement.addElement(entityClass.getSimpleName());
				Element seqElement = element.addElement("Seq");// 增加序号字段
				seqElement.addCDATA(String.valueOf((pageNum - 1) * pageSize + i	+ 1));
				this.createElement(entity, element);
			}
		}
		return document;
	}
	/*
	 * 生成节点xml文档对象(实体类型)
	 */
	@SuppressWarnings("rawtypes")
	private Document createDocument(Object entity) throws Exception {
		Class entityClass = entity.getClass();
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rootElement = document.addElement(entityClass.getSimpleName());
		this.createElement(entity, rootElement);
		return document;
	}
	/*
	 * 生成节点xml 支持实体的属性类型为：String、Integer、Date、Double、Long、List、BaseEntity类型的实体
	 */
	@SuppressWarnings("rawtypes")
	private void createElement(Object entity, Element element) throws Exception {
		Class entityClass = entity.getClass();
		Method[] methods = entityClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Class returnClass = methods[i].getReturnType();
			String returnType = methods[i].getReturnType().getSimpleName();
			
			if (methods[i].getName().startsWith("get")&& !"Class".equals(returnType)) {
				String filed = methods[i].getName().substring(3);
				Object obj = methods[i].invoke(entity, new Object[0]);
				String value = "";
				if (returnClass.isPrimitive()) {
					value = Kit.getObjStr(obj);
				} else if ("String".equals(returnType)) {
					value = Kit.getObjStr(obj);
				} else if ("Date".equals(returnType)) {
					if (obj != null) value = Kit.formatDateTime(((Date) obj), "yyyy-MM-dd HH:mm:ss");
				} else if ("Integer".equals(returnType)) {
					value = Kit.getObjStr(obj);
				} else if ("Double".equals(returnType)) {
					value = Kit.getObjStr(obj);
				} else if ("Long".equals(returnType)) {
					value = Kit.getObjStr(obj);
				} else if ("List".equals(returnType)) {// 集合类型
					if (obj != null) {
						Element listElement = element.addElement(filed);
						List list = (List) obj;
						for (int j = 0; j < list.size(); j++) {
							Object o = list.get(j);
							Element e = listElement.addElement(o.getClass().getSimpleName());
							createElement(o, e);// 递归产生子实体

						}
						continue;
					}
				} else if (obj != null&& returnClass != null&& "BaseEntity".equals(returnClass.getSuperclass().getSimpleName())) {
					Element entityElement = element.addElement(filed);
					createElement(obj, entityElement);// 递归产生子实体

					continue;
				}
				Element filedElement = element.addElement(filed);
				if("Id".equals(filed)){//通过attribute记录id
					element.addAttribute("Id", value);
					filedElement.addCDATA(value);
				}else{
					filedElement.addCDATA(value);
				}
			}
		}
	}

}
