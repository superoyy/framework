package com.timanetworks.iov.core.mongodb.handler.impl;

import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.timanetworks.iov.core.mongodb.entity.BaseDocumentEntity;
import com.timanetworks.iov.core.mongodb.dao.MongodbBaseDao;
import com.timanetworks.iov.core.mongodb.handler.MongodbHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-25
 * Time: 上午11:31
 * To change this template use File | Settings | File Templates.
 */
@Service("mongodbHandler")
public class MongodbHandlerImpl implements MongodbHandler,ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MongodbHandlerImpl.class);


    @Autowired
    private GridFsTemplate gridFsTemplate;

    private Map<String,MongodbBaseDao> daoMap=new HashMap<>();

    private MongodbBaseDao getDao(String className){
        MongodbBaseDao dao=this.daoMap.get(className);
        if(dao==null){
            logger.error("Not found {} Register Dao.",className);
        }
        return dao;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(event.getApplicationContext()!=null){
            Map<String,MongodbBaseDao> daoCollection=event.getApplicationContext().getBeansOfType(MongodbBaseDao.class);
            for (MongodbBaseDao dao:daoCollection.values()){
                logger.info("{} Register DaoMap:{} -> {}",this.getClass().getTypeName(),dao.getEntityClazz().getCanonicalName(),dao.getClass().getCanonicalName());
                this.daoMap.put(dao.getEntityClazz().getCanonicalName(), dao);
            }
        }

    }


    @Override
    public void setDaoMap(Collection<MongodbBaseDao> daoCollection) {
        for (MongodbBaseDao dao:daoCollection){
            this.daoMap.put(dao.getEntityClazz().getCanonicalName(), dao);
        }
    }

    @Override
    public <T> T getEntityById(Class<T> entityClazz, String id) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return (T) dao.getEntityById(id);
    }

    @Override
    public long getEntityCountAll(Class entityClazz) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityCountAll();
    }

    @Override
    public <T> List<T> getEntityAll(Class<T> entityClazz) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityAll();
    }

    @Override
    public <T> List<T> getEntityAll(Class<T> entityClazz, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityAll(orderProps);
    }

    @Override
    public <T> List<T> getEntityAll(Class<T> entityClazz, int start, int count, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityAll(start,count,orderProps);
    }

    @Override
    public long getEntityCountByProps(Class entityClazz, Map<String, Object> params) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityCountByProps(params);
    }

    @Override
    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByProps(params,orderProps);
    }

    @Override
    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByProps(params);
    }

    @Override
    public <T> List<T> getEntityListByProps(Class<T> entityClazz, Map<String, Object> params, int start, int count, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByProps(params,start,count,orderProps);
    }

    @Override
    public long getEntityCountByProp(Class entityClazz, String propKey, Object propValue) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityCountByProp(propKey,propValue);
    }

    @Override
    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByProp(propKey,propValue,orderProps);
    }

    @Override
    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByProp(propKey,propValue);
    }

    @Override
    public <T> List<T> getEntityListByProp(Class<T> entityClazz, String propKey, Object propValue, int start, int count, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByProp(propKey,propValue,start,count,orderProps);
    }

    @Override
    public long getEntityCountByCriteria(Class entityClazz, Criteria criteria) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityCountByCriteria(criteria);
    }

    @Override
    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria, int start, int count, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByCriteria(criteria,start,count,orderProps);
    }

    @Override
    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria, Map<String, String> orderProps) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByCriteria(criteria,orderProps);
    }

    @Override
    public <T> List<T> getEntityListByCriteria(Class<T> entityClazz, Criteria criteria) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.getEntityListByCriteria(criteria);
    }

    @Override
    public String addEntity(BaseDocumentEntity entity) throws Exception {
        MongodbBaseDao dao=this.getDao(entity.getClass().getCanonicalName());
        return dao.insertEntity(entity);
    }


    @Override
    public WriteResult modifyEntity(BaseDocumentEntity entity) throws Exception {
        MongodbBaseDao dao=this.getDao(entity.getClass().getCanonicalName());
        return dao.updateEntity(entity);
    }

    @Override
    public <T> WriteResult modifyEntity(Class<T> entityClazz, String id, Map<String, Object> kv) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.updateEntity(id,kv);
    }

    @Override
    public <T> WriteResult modifyEntity(Class<T> entityClazz, String id, String propKey, Object propValue) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.updateEntity(id,propKey,propValue);
    }

    @Override
    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, Criteria criteria, Map<String, Object> kv) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.updateEntityByCriteria(criteria, kv);
    }

    @Override
    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz,Criteria criteria,Update update)  throws Exception{
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.updateEntityByCriteria(criteria, update);
    }


    @Override
    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, Map<String, Object> params, Map<String, Object> kv) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.updateEntityByProps(params,kv);
    }

    @Override
    public <T> WriteResult modifyEntityBatch(Class<T> entityClazz, String propKey, Object propValue, Map<String, Object> kv) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        return dao.updateEntityByProp(propKey, propValue, kv);
    }

    @Override
    public <T> void removeEntity(Class<T> entityClazz, String id) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        dao.deleteEntity(id);
    }

    @Override
    public <T> void removeEntityAll(Class<T> entityClazz) throws Exception{
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        dao.deleteEntityAll();
    }


    @Override
    public <T> void removeEntityBatch(Class<T> entityClazz, Criteria criteria) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        dao.deleteEntityByCriteria(criteria);
    }

    @Override
    public <T> void removeEntityBatch(Class<T> entityClazz, Map<String, Object> params) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        dao.deleteEntityByProps(params);
    }

    @Override
    public <T> void removeEntityBatch(Class<T> entityClazz, String propKey, Object propValue) throws Exception {
        MongodbBaseDao dao=this.getDao(entityClazz.getCanonicalName());
        dao.deleteEntityByProp(propKey, propValue);
    }

    @Override
    public GridFSFile saveFile(InputStream content, String filename, String contentType, Map<String,Object> metadata){
        return this.gridFsTemplate.store(content,filename,contentType,metadata);
    }

    @Override
    public void removeFile(Criteria criteria){
        Query query = new Query(criteria);
        this.gridFsTemplate.delete(query);
    }

    @Override
    public List<GridFSDBFile> getFiles(Criteria criteria){
        return this.getFiles(criteria,0,0,null);
    }

    public List<GridFSDBFile> getFiles(Criteria criteria,Map<String, String> orderProps){
        return this.getFiles(criteria,0,0,orderProps);
    }

    @Override
    public List<GridFSDBFile> getFiles(Criteria criteria,int start,int count){
        return this.getFiles(criteria,start,count,null);
    }

    @Override
    public List<GridFSDBFile> getFiles(Criteria criteria,int start,int count,Map<String, String> orderProps){
        Query query = new Query(criteria);
        if (orderProps != null) {
            for (String key : orderProps.keySet()) {
                if ("ASC".equals(orderProps.get(key).toUpperCase())) {
                    Sort sort=new Sort(Sort.Direction.ASC,key);
                    query.with(sort);
                } else if ("DESC".equals(orderProps.get(key).toUpperCase())) {
                    Sort sort=new Sort(Sort.Direction.DESC,key);
                    query.with(sort);
                }
            }
        }
        int skip = start < 0 ? 0 : start;
        int limit = count < 0 ? 0 : count;
        if (skip >= 0 && limit >= 1) {
            query.skip(skip);
            query.limit(limit);
        }
        return this.gridFsTemplate.find(query);
    }

    @Override
    public GridFSDBFile getFileOne(Criteria criteria){
        Query query = new Query(criteria);
        return this.gridFsTemplate.findOne(query);
    }

    @Override
    public GridFSDBFile getFileById(String id) {
        return getFileOne(Criteria.where("_id").is(id));
    }

    @Override
    public void removeFileById(String id) {
        removeFile(Criteria.where("_id").is(id));
    }


}
