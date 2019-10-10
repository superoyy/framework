package com.timanetworks.iov.core.mongodb.entity;

import com.timanetworks.iov.util.json.JsonGenerater;
import com.timanetworks.iov.util.json.JsonGeneratorImp;
import com.timanetworks.iov.util.xml.XmlGenerater;
import com.timanetworks.iov.util.xml.XmlGeneraterDom4jImp;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public abstract class BaseDocumentEntity implements Serializable{

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 根据实体对象产生xml
     * xmlHandler:处理xml的解析器
     */
    public String genEntityXml() throws Exception{
        XmlGenerater xmlGenerater=new XmlGeneraterDom4jImp();
        return xmlGenerater.createXml(this);
    }

    /**
     * 根据实体对象产生Json
     */
    public String genEntityJson() throws Exception{
        JsonGenerater jsonGenerater=new JsonGeneratorImp();
        return jsonGenerater.createJson(this,1);
    }





}
