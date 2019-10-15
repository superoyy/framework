package com.dukla.base.mongodb.handler;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.dukla.base.mongodb.entity.BaseDocumentEntity;
import com.dukla.base.mongodb.dao.MongodbBaseDao;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Mongodb接口
 * 欧阳亚
 * 2013.1
 */
public interface MongodbHandler {
    public void setDaoMap(Collection<MongodbBaseDao> daoCollection);

    public <T> T getEntityById(Class<T> entityClazz, String id) throws Exception;

    public long getEntityCountAll(Class entityClazz) throws Exception;

    public <T> List<T> getEntityAll(Class<T> entityClazz) throws Exception;

    public <T> List<T> getEntityAll(Class<T> entityClazz, Map<String, String> orderProps) throws Exception;

    public <T> List<T> getEntityAll(Class<T> entityClazz, int start, int count, Map<String, String> orderProps) throws Exception;

    public long getEntityCountByProps(Class entityClazz, Map<String, Object> params)  throws Exception;

    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params, Map<String, String> orderProps) throws Exception;

    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params) throws Exception;

    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params, int start, int count, Map<String, String> orderProps) throws Exception;

    public long getEntityCountByProp(Class entityClazz, String propKey, Object propValue)  throws Exception;

    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue, Map<String, String> orderProps) throws Exception;

    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue) throws Exception;

    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue, int start, int count, Map<String, String> orderProps) throws Exception;

    public long getEntityCountByCriteria(Class entityClazz, Criteria criteria) throws Exception;

    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria, int start, int count, Map<String, String> orderProps) throws Exception;

    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria, Map<String, String> orderProps) throws Exception;

    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria) throws Exception;

    public String addEntity(BaseDocumentEntity entity) throws Exception;

    public UpdateResult modifyEntity(BaseDocumentEntity entity) throws Exception;

    public <T> UpdateResult modifyEntity(Class<T> entityClazz, String id, Map<String, Object> kv) throws Exception;

    public <T> UpdateResult modifyEntity(Class<T> entityClazz, String id, String propKey, Object propValue) throws Exception;

    public <T> UpdateResult modifyEntityBatch(Class<T> entityClazz, Criteria criteria, Map<String, Object> kv) throws Exception;

    public <T> UpdateResult modifyEntityBatch(Class<T> entityClazz, Criteria criteria, Update update)  throws Exception;

    public <T> UpdateResult modifyEntityBatch(Class<T> entityClazz, Map<String, Object> params, Map<String, Object> kv) throws Exception;

    public <T> UpdateResult modifyEntityBatch(Class<T> entityClazz, String propKey, Object propValue, Map<String, Object> kv) throws Exception;

    public <T> void removeEntity(Class<T> entityClazz, String id) throws Exception;

    public <T> void removeEntityAll(Class<T> entityClazz) throws Exception;

    public <T> void removeEntityBatch(Class<T> entityClazz, Criteria criteria) throws Exception;

    public <T> void removeEntityBatch(Class<T> entityClazz, Map<String, Object> params) throws Exception;

    public <T> void removeEntityBatch(Class<T> entityClazz, String propKey, Object propValue) throws Exception;

    public ObjectId saveFile(InputStream content, String filename, String contentType, Map<String, Object> meta);

    public GridFSFile getFileById(String id);

    public void removeFileById(String id);

    public void removeFile(Criteria criteria);

    public GridFSFindIterable getFiles(Criteria criteria);

    public GridFSFindIterable getFiles(Criteria criteria, Map<String, String> orderProps);

    public GridFSFindIterable getFiles(Criteria criteria, int start, int count);

    public GridFSFindIterable getFiles(Criteria criteria, int start, int count, Map<String, String> orderProps);

    public GridFSFile getFileOne(Criteria criteria);



}
