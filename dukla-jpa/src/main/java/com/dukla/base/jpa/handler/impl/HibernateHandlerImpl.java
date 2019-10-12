package com.dukla.base.jpa.handler.impl;

import com.dukla.base.jpa.dao.HibernateBaseDao;
import com.dukla.base.jpa.dao.QueryParam;
import com.dukla.base.jpa.entity.BaseEntity;
import com.dukla.base.jpa.handler.HibernateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过Hibernate访问数据服务实现
 * @version 2.0
 * @author 欧阳亚
 * @since 2018.1
 */
@Service("hibernateHandler")
public class HibernateHandlerImpl implements HibernateHandler,ApplicationListener<ApplicationStartedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(HibernateHandlerImpl.class);

    private Map<String,HibernateBaseDao> daoMap=new HashMap<>();

    private HibernateBaseDao getDao(String className){
        HibernateBaseDao dao=this.daoMap.get(className);
        if(dao==null){
            logger.error("Not found {} Register Dao.",className);
        }
        return dao;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        if(event.getApplicationContext()!=null){
            Map<String,HibernateBaseDao> daoCollection=event.getApplicationContext().getBeansOfType(HibernateBaseDao.class);
            for (HibernateBaseDao dao:daoCollection.values()){
                logger.info("{} Register DaoMap:{} -> {}",this.getClass().getTypeName(),dao.getEntityClazz().getCanonicalName(),dao.getClass().getCanonicalName());
                this.daoMap.put(dao.getEntityClazz().getCanonicalName(), dao);
            }
        }
    }


    @Transactional
    @Override
    public String addEntity(BaseEntity entity){
        HibernateBaseDao dao=this.getDao(entity.getClass().getCanonicalName());
        return dao.insertEntity(entity);
    }

    @Transactional
    @Override
    public void modifyEntity(BaseEntity entity){
        HibernateBaseDao dao=this.getDao(entity.getClass().getCanonicalName());
        dao.updateEntity(entity);
    }

    @Transactional
    @Override
    public void removeEntity(BaseEntity entity){
        HibernateBaseDao dao=this.getDao(entity.getClass().getCanonicalName());
        dao.deleteEntity(entity);
    }

    @Transactional
    @Override
    public <T> void removeEntityByProperty(Class<T> entityClass, String propertyName, Object propertyValue){
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        dao.deleteEntityByProperty(propertyName, propertyValue);
    }

    @Transactional
    @Override
    public <T> void removeEntityByPropertys(Class<T> entityClass, Map<String, Object> param){
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        dao.deleteEntityByPropertys(param);
    }

    @Transactional
    @Override
    public <T> void removeEntityByByQueryParam(Class<T> entityClass, List<QueryParam> queryParamList){
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        dao.deleteEntityByByQueryParam(queryParamList);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> T getEntityById(Class<T> entityClass, String id){
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return (T) dao.getEntityById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByProperty(Class<T> entityClass, String propertyName, Object propertyValue) {
        return this.getEntityListByProperty(entityClass,propertyName,propertyValue,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByProperty(Class<T> entityClass, String propertyName, Object propertyValue, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListByProperty(propertyName,propertyValue,orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByProperty(Class<T> entityClass, String propertyName, Object propertyValue, int start, int count){
        return this.getEntityListByProperty(entityClass,propertyName,propertyValue,start, count,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByProperty(Class<T> entityClass, String propertyName, Object propertyValue, int start, int count, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListByProperty(propertyName,propertyValue,start,count,orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> int getEntityCountByProperty(Class<T> entityClass, String propertyName, Object propertyValue) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityCountByProperty(propertyName,propertyValue);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByPropertys(Class<T> entityClass, Map<String, Object> param, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListByPropertys(param,orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByPropertys(Class<T> entityClass, Map<String, Object> param) {
        return this.getEntityListByPropertys(entityClass,param,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> int getEntityCountByPropertys(Class<T> entityClass, Map<String, Object> param) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityCountByPropertys(param);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByPropertys(Class<T> entityClass, Map<String, Object> param, int start, int count, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListByPropertys(param,start,count,orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByPropertys(Class<T> entityClass, Map<String, Object> param, int start, int count) {
        return this.getEntityListByPropertys(entityClass,param,start,count,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListAll(Class<T> entityClass, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListAll(orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListAll(Class<T> entityClass) {
        return this.getEntityListAll(entityClass,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> int getEntityCountAll(Class<T> entityClass) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityCountAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListAll(Class<T> entityClass, int start, int count, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListAll(start, count,orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListAll(Class<T> entityClass, int start, int count) {
        return this.getEntityListAll(entityClass,start,count,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> int getEntityCountByQueryParam(Class<T> entityClass, List<QueryParam> queryParamList) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityCountByQueryParam(queryParamList);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByQueryParam(Class<T> entityClass, List<QueryParam> queryParamList, Map<String, String> orderProps) {
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListByQueryParam(queryParamList,orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByQueryParam(Class<T> entityClass, List<QueryParam> queryParamList) {
        return this.getEntityListByQueryParam(entityClass,queryParamList,null);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByQueryParam(Class<T> entityClass, List<QueryParam> queryParamList, int start, int count, Map<String, String> orderProps){
        HibernateBaseDao dao=this.getDao(entityClass.getCanonicalName());
        return dao.getEntityListByQueryParam(queryParamList, start, count, orderProps);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public <T> List<T> getEntityListByQueryParam(Class<T> entityClass, List<QueryParam> queryParamList, int start, int count) {
        return this.getEntityListByQueryParam(entityClass,queryParamList,start,count,null);
    }

}
