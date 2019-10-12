package com.dukla.base.mongodb.dao;

import com.dukla.base.mongodb.entity.BaseDocumentEntity;
import com.dukla.base.util.Kit;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-22
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class MongodbBaseDao<T extends BaseDocumentEntity> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    private Class<T> entityClazz;

    public Class<T> getEntityClazz() {
        if (this.entityClazz == null) {
            this.entityClazz = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.entityClazz;
    }

    /**
     * 插入实体
     */
    public String insertEntity(T entity) {
        String id;
        if (entity.getId() != null) {
            id = entity.getId();
        } else {
            id = Kit.get36UUID();
            entity.setId(id);
        }
        this.mongoTemplate.insert(entity);
        return id;
    }

    /**
     * 得到实体
     */
    public T getEntityById(String id) {
        return this.mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), this.getEntityClazz());
    }

    public List<T> getEntityAll(int start, int count, Map<String, String> orderProps) {
        return this.getEntityListByCriteria(new Criteria(), start, count, orderProps);
    }

    public List<T> getEntityAll(Map<String, String> orderProps) {
        return this.getEntityAll(0, 0, orderProps);
    }

    public List<T> getEntityAll() {
        return this.getEntityAll(null);
    }

    public long getEntityCountAll() {
        return this.getEntityCountByCriteria(new Criteria());
    }

    public List<T> getEntityListByProp(String propKey, Object propValue, Map<String, String> orderProps) {
        return this.getEntityListByProp(propKey, propValue, 0, 0, orderProps);
    }

    public List<T> getEntityListByProp(String propKey, Object propValue) {
        return this.getEntityListByProp(propKey, propValue, null);
    }

    public List<T> getEntityListByProp(String propKey, Object propValue, int start, int count, Map<String, String> orderProps) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(propKey, propValue);
        return this.getEntityListByProps(params, start, count, orderProps);
    }

    public long getEntityCountByProp(String propKey, Object propValue) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(propKey, propValue);
        return this.getEntityCountByProps(params);
    }

    public List<T> getEntityListByProps(Map<String, Object> params, Map<String, String> orderProps) {
        return this.getEntityListByProps(params, 0, 0, orderProps);
    }

    public List<T> getEntityListByProps(Map<String, Object> params) {
        return this.getEntityListByProps(params, null);
    }

    public List<T> getEntityListByProps(Map<String, Object> params, int start, int count, Map<String, String> orderProps) {
        Criteria criteria = new Criteria();
        for (String key : params.keySet()) {
            criteria.and(key).is(params.get(key));
        }
        return this.getEntityListByCriteria(criteria, start, count, orderProps);
    }

    public long getEntityCountByProps(Map<String, Object> params) {
        Criteria criteria = new Criteria();
        for (String key : params.keySet()) {
            criteria.and(key).is(params.get(key));
        }
        return this.getEntityCountByCriteria(criteria);
    }

    public List<T> getEntityListByCriteria(Criteria criteria, int start, int count, Map<String, String> orderProps) {
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
        return this.mongoTemplate.find(query, this.getEntityClazz());
    }

    public List<T> getEntityListByCriteria(Criteria criteria, Map<String, String> orderProps) {
        return this.getEntityListByCriteria(criteria, 0, 0, orderProps);
    }

    public List<T> getEntityListByCriteria(Criteria criteria) {
        return this.getEntityListByCriteria(criteria, null);
    }

    public long getEntityCountByCriteria(Criteria criteria) {
        Query query = new Query(criteria);
        return this.mongoTemplate.count(query, this.getEntityClazz());
    }

    private Map<String, Object> getEntityFields(T entity) {
        Map<String, Object> kv = new HashMap<String, Object>();
        Method[] methods = entity.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && !"Class".equals(method.getReturnType().getSimpleName())) {
                try {
                    String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                    Object value = method.invoke(entity, new Object[0]);
                    kv.put(key, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return kv;
    }

    private Update genUpdate(Map<String, Object> kv) {
        Update update = new Update();
        for (String key : kv.keySet()) {
            if (!key.equals("id")) {
                Object value = kv.get(key);
                if (value == null) {
                    update.unset(key);
                } else {
                    update.set(key, kv.get(key));
                }
            }
        }
        return update;
    }

    /**
     * 修改实体
     */
    public UpdateResult updateEntity(T entity) {
        Map<String, Object> kv = this.getEntityFields(entity);
        return this.updateEntity(entity.getId(), kv);
    }

    public UpdateResult updateEntity(String id, String propKey, Object propValue) {
        Map<String, Object> kv = new HashMap<String, Object>();
        kv.put(propKey, propValue);
        return this.updateEntity(id, kv);
    }

    public UpdateResult updateEntity(String id, Map<String, Object> kv) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = this.genUpdate(kv);
        return this.mongoTemplate.updateFirst(query, update, this.getEntityClazz());
    }

    public UpdateResult updateEntityByCriteria(Criteria criteria, Map<String, Object> kv) {
        Update update = this.genUpdate(kv);
        return this.updateEntityByCriteria(criteria, update);
    }

    public UpdateResult updateEntityByCriteria(Criteria criteria, Update update) {
        Query query = new Query(criteria);
        return this.mongoTemplate.updateMulti(query, update, this.getEntityClazz());
    }

    public UpdateResult updateEntityByProps(Map<String, Object> params, Map<String, Object> kv) {
        Criteria criteria = new Criteria();
        for (String key : params.keySet()) {
            criteria.and(key).is(params.get(key));
        }
        return this.updateEntityByCriteria(criteria, kv);
    }

    public UpdateResult updateEntityByProp(String propKey, Object propValue, Map<String, Object> kv) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(propKey, propValue);
        return this.updateEntityByProps(params, kv);
    }

    /**
     * 删除实体
     */
    public void deleteEntity(String id) {
        this.deleteEntityByProp("id", id);
    }

    public void deleteEntityByCriteria(Criteria criteria) {
        Query query = new Query(criteria);
        this.mongoTemplate.remove(query, this.getEntityClazz());
    }

    public void deleteEntityAll() {
        this.deleteEntityByCriteria(new Criteria());
    }

    public void deleteEntityByProps(Map<String, Object> params) {
        Criteria criteria = new Criteria();
        for (String key : params.keySet()) {
            criteria.and(key).is(params.get(key));
        }
        this.deleteEntityByCriteria(criteria);
    }

    public void deleteEntityByProp(String propKey, Object propValue) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(propKey, propValue);
        this.deleteEntityByProps(params);
    }
}
