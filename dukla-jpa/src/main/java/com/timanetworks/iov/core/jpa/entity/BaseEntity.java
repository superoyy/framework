package com.timanetworks.iov.core.jpa.entity;

import com.timanetworks.iov.util.json.JsonGenerater;
import com.timanetworks.iov.util.json.JsonGeneratorImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

    /**
     * 产生实体json串
     */
    public String genEntityJsonStr(){
		JsonGenerater jsonGenerater=new JsonGeneratorImp();
        String json="{}";
        try {
            json=jsonGenerater.createJson(this,JsonGenerater.KEY_VALUE_STYLE_QUOT);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return json;
    }


}
