package com.dukla.base.mongodb.handler;

import com.dukla.base.mongodb.entity.BaseDocumentEntity;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Mongodb接口
 * 欧阳亚
 * 2013.1
 */
public interface MongodbHandler {

    public void registerDaoMap(ApplicationContext applicationContext);

    public <T> T getEntityById(Class<T> entityClazz, String id) ;

    public long getEntityCountAll(Class entityClazz) ;

    public <T> List<T> getEntityAll(Class<T> entityClazz) ;

    public <T> List<T> getEntityAll(Class<T> entityClazz, Map<String, String> orderProps) ;

    public <T> List<T> getEntityAll(Class<T> entityClazz, int start, int count, Map<String, String> orderProps) ;

    public long getEntityCountByProps(Class entityClazz, Map<String, Object> params)  ;

    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params, Map<String, String> orderProps) ;

    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params) ;

    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params, int start, int count, Map<String, String> orderProps) ;

    public long getEntityCountByProp(Class entityClazz, String propKey, Object propValue)  ;

    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue, Map<String, String> orderProps) ;

    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue) ;

    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue, int start, int count, Map<String, String> orderProps) ;

    public long getEntityCountByCriteria(Class entityClazz, Criteria criteria) ;

    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria, int start, int count, Map<String, String> orderProps) ;

    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria, Map<String, String> orderProps) ;

    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria) ;

    public String addEntity(BaseDocumentEntity entity) ;

    public WriteResult modifyEntity(BaseDocumentEntity entity) ;

    public <T> WriteResult modifyEntity(Class<T> entityClazz, String id, Map<String, Object> kv) ;

    public <T> WriteResult modifyEntity(Class<T> entityClazz, String id, String propKey, Object propValue) ;

    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, Criteria criteria, Map<String, Object> kv) ;

    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, Criteria criteria, Update update)  ;

    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, Map<String, Object> params, Map<String, Object> kv) ;

    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, String propKey, Object propValue, Map<String, Object> kv) ;

    public <T> void removeEntity(Class<T> entityClazz, String id) ;

    public <T> void removeEntityAll(Class<T> entityClazz) ;

    public <T> void removeEntityBatch(Class<T> entityClazz, Criteria criteria) ;

    public <T> void removeEntityBatch(Class<T> entityClazz, Map<String, Object> params) ;

    public <T> void removeEntityBatch(Class<T> entityClazz, String propKey, Object propValue) ;

    public GridFSFile saveFile(InputStream content, String filename, String contentType, Map<String, Object> meta);

    public GridFSDBFile getFileById(String id);

    public void removeFileById(String id);

    public void removeFile(Criteria criteria);

    public List<GridFSDBFile> getFiles(Criteria criteria);

    public List<GridFSDBFile> getFiles(Criteria criteria, Map<String, String> orderProps);

    public List<GridFSDBFile> getFiles(Criteria criteria, int start, int count);

    public List<GridFSDBFile> getFiles(Criteria criteria, int start, int count, Map<String, String> orderProps);

    public GridFSDBFile getFileOne(Criteria criteria);



}