package com.timanetworks.iov.util.json;

import com.timanetworks.iov.util.Kit;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;


public class JsonGeneratorImp implements JsonGenerater,Serializable {

    private static final long serialVersionUID = 2322283463542736904L;
	
	private static String getJsonStringValue(Object obj){
		String value= Kit.getObjStr(obj);
//	    //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
//		if(value.contains("'")){
//			value = value.replaceAll("'", "\\'");
//		}
//	    //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
//		if(value.contains("\"")){
//			value = value.replaceAll("\"", "\\\"");
//		}
//	    //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
//		if(value.contains("\r\n")){
//			value = value.replaceAll("\r\n", "\\u000d\\u000a");
//		}
//	    //将换行转换一下，因为JSON串中字符串不能出现显式的换行
//		if(value.contains("\n")){
//			value = value.replaceAll("\n", "\\u000a");
//		}
		return value.replaceAll("\r\n","")
                .replaceAll("\r","")
                .replaceAll("\n","")
                .replaceAll("<br>","")
                .replaceAll("<br/>","")
                .replaceAll("<a>","")
                .replaceAll("</a>","")
                .replaceAll("\"","'");
	}

    @SuppressWarnings("rawtypes")
    private void createJsonObject(Object entity,StringBuffer sb,int keyValueStyle) throws Exception {
        Class entityClass = entity.getClass();
        Method[] methods = entityClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Class returnClass = methods[i].getReturnType();
            String returnType = methods[i].getReturnType().getSimpleName();
            if (methods[i].getName().startsWith("get") && !"Class".equals(returnType)) {
                String field = methods[i].getName().substring(3,4).toLowerCase()+methods[i].getName().substring(4);
                Object obj = methods[i].invoke(entity,new Object[0]);
                String value = "";
                if (returnClass.isPrimitive()) {
                    value = Kit.getObjStr(obj);
                } else if ("String".equals(returnType)) {
                    value = getJsonStringValue(obj);
                } else if ("Date".equals(returnType)) {
                    if (obj != null) {
                        value = Kit.formatDateTime(((Date) obj), "yyyy-MM-dd HH:mm:ss");
                    }
                } else if ("Integer".equals(returnType)) {
                    value = Kit.getObjStr(obj);
                } else if ("Double".equals(returnType)) {
                    value = Kit.getObjStr(obj);
                } else if ("Long".equals(returnType)) {
                    value = Kit.getObjStr(obj);
                } else if (obj != null && returnClass != null && returnClass.getSuperclass()!=null && ("BaseEntity".equals(returnClass.getSuperclass().getSimpleName()) || "BaseDocumentEntity".equals(returnClass.getSuperclass().getSimpleName()))) {
                    if(keyValueStyle==1){
                        sb.append("\""+field+"\":{");
                    }else{
                        sb.append(field+":{");
                    }
                    createJsonObject(obj, sb,keyValueStyle);// 递归产生子实体
                    sb.append("},");
                    continue;
                }
                if(keyValueStyle==1){
                    sb.append("\""+field+"\":\""+value+"\",");
                }else{
                    sb.append(field+":\""+value+"\",");
                }
            }
        }
        int lastIndex=sb.lastIndexOf(",");
        if(lastIndex!=-1){
            sb.replace(lastIndex, lastIndex+1, "");
        }
    }

    /**
     * 根据实体对象产生Json
     * keyValueStyle:键值对样式
     * 0:普通样式{key:'value'}
     * 1:用引号包裹形如{"key":"value"}
     */
    @Override
    public String createJson(Object entity,int keyValueStyle) throws Exception {
        StringBuffer sb=new StringBuffer();
        sb.append("{");
        this.createJsonObject(entity, sb,keyValueStyle);
        sb.append("}");
        return sb.toString();
    }


}
