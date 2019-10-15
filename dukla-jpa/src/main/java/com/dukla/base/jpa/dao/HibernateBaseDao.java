package com.dukla.base.jpa.dao;

import com.dukla.base.jpa.entity.BaseEntity;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基于Hibernate的Dao基类
 * @version 1.3
 * @author dukla.ou
 * @since 2019.10
 */ 
public abstract class HibernateBaseDao<T extends BaseEntity>{

    private Class<T> entityClazz;

    static final Logger logger = LoggerFactory.getLogger(HibernateBaseDao.class);

    @Autowired
    EntityManagerFactory entityManagerFactory;

    SessionFactory getSessionFactory() {


        if(entityManagerFactory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }


    public Class<T> getEntityClazz() {
        if (this.entityClazz == null) {
            this.entityClazz = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
            logger.debug("T class = " + this.entityClazz.getCanonicalName());
        }
        return this.entityClazz;
    }

	/**
	 * for genWhereSql
	 */
	private class ParamPos{
    	private int pos;

    	public ParamPos(int start){
    		pos=start;
		}

		public int getPos() {
			return pos;
		}

		public ParamPos add(){
    		pos++;
    		return this;
		}
	}

    /**
	 * 拼装排序hql
	 */
	private String genOrderHql(String hql,Map<String,String> orderProps){
		StringBuilder orderStr=new StringBuilder();
		orderStr.append(hql);
		if(orderProps!=null && orderProps.size()!=0){
			orderStr.append(" order by ");
			int k=0;
			for(String key:orderProps.keySet()){
				if(k!=orderProps.keySet().size()-1){
					orderStr.append(" _t001."+key+" "+(orderProps.get(key)==null?"":("asc".equals(orderProps.get(key)) || "desc".equals(orderProps.get(key))? orderProps.get(key):""))+",");
				}else{
					orderStr.append(" _t001."+key+" "+(orderProps.get(key)==null?"":("asc".equals(orderProps.get(key)) || "desc".equals(orderProps.get(key))? orderProps.get(key):"")));
				}
				k++;
			}
		}
		return orderStr.toString();
	}
	/**
	 * 拼装条件hql
	 */
	private String genWhereHql(String hql, List<Object> params, QueryParam queryParam, ParamPos paramPos){
        StringBuilder whereStr=new StringBuilder();
		whereStr.append(hql);
		//in 条件
		if(("in".equals(queryParam.getOperator()) || "not in".equals(queryParam.getOperator())) 
				&& queryParam.getParamValue()!=null 
				&& queryParam.getParamValue() instanceof Object[]){
			
			Object[] inParams=(Object[]) queryParam.getParamValue();
			if(inParams.length!=0){
				whereStr.append(queryParam.getConOper())
						.append(" _t001.")
						.append(queryParam.getParamName())
						.append(" ")
						.append(queryParam.getOperator())
						.append(" ( ");
				for(int i=0;i<inParams.length;i++){
					if(i==inParams.length-1){
						whereStr.append(" ?")
								.append(paramPos.getPos())
								.append(" ) ");
					}else{
						whereStr.append(" ?")
								.append(paramPos)
								.append(", ");
					}
					paramPos.add();
					params.add(inParams[i]);
				}
			}
		//between 条件
		}else if("between".equals(queryParam.getOperator()) 
				&& queryParam.getParamValue()!=null 
				&& queryParam.getParamValue() instanceof Object[]){
			
			Object[] betweenParams=(Object[]) queryParam.getParamValue();
			if(betweenParams.length>1){
				whereStr.append(queryParam.getConOper())
						.append(" _t001.")
						.append(queryParam.getParamName())
						.append(" ")
						.append(queryParam.getOperator())
						.append(" ?")
						.append(paramPos.getPos()+1)
						.append(" and ?")
						.append(paramPos.getPos()+2)
						.append(" ");
				params.add(betweenParams[0]);
				params.add(betweenParams[1]);
				paramPos.add().add();
			}
		//is 条件
		}else if("is".equals(queryParam.getOperator()) && ("null".equals(queryParam.getParamValue()) 
				|| "not null".equals(queryParam.getParamValue()))){
			
			whereStr.append(queryParam.getConOper())
					.append(" _t001.")
					.append(queryParam.getParamName())
					.append(" ")
					.append(queryParam.getOperator())
					.append(" ")
					.append(queryParam.getParamValue().toString())
					.append(" ");
		//其他条件
		}else if("=".equals(queryParam.getOperator()) 
				|| "like".equals(queryParam.getOperator()) 
				|| "not like".equals(queryParam.getOperator()) 
				|| ">".equals(queryParam.getOperator()) 
				|| ">=".equals(queryParam.getOperator()) 
				|| "<".equals(queryParam.getOperator()) 
				|| "<=".equals(queryParam.getOperator()) 
				|| "<>".equals(queryParam.getOperator())){
			
			whereStr.append(queryParam.getConOper())
					.append(" _t001.")
					.append(queryParam.getParamName())
					.append(" ")
					.append(queryParam.getOperator())
					.append(" ?")
					.append(paramPos.getPos())
					.append(" ");
			params.add(queryParam.getParamValue());
			paramPos.add();
	    }
		return whereStr.toString();
	}

    /**
     * session解除绑定
     * @param entity
     */
    public void evictEntity(T entity){
        this.getSessionFactory().getCurrentSession().evict(entity);
    }

	/**
	 * 插入一个实体
	 */
	public String insertEntity(T entity){
        logger.debug("insert entity " + entity.getClass().getCanonicalName());
        return (String) this.getSessionFactory().getCurrentSession().save(entity);
    }
	/**
	 * 修改一个实体
	 */
	public void updateEntity(T entity){
        logger.debug("update entity "+entity.getClass().getCanonicalName());
        this.getSessionFactory().getCurrentSession().update(entity);
    }
	/**
	 * 删除一个实体
	 */
	public void deleteEntity(T entity){
        logger.debug("delete entity "+entity.getClass().getCanonicalName());
        this.evictEntity(entity);
        this.getSessionFactory().getCurrentSession().delete(entity);
    }

	/**
	 * 根据属性删除实体
	 */
	public void deleteEntityByProperty(String propertyName,Object propertyValue){
		String hql="delete "+this.getEntityClazz().getCanonicalName()+" _t001 where _t001."+propertyName+"=?1";
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(1, propertyValue);
        query.executeUpdate();
	}
	/**
	 * 根据属性集合删除实体
	 */
	public void deleteEntityByPropertys(Map<String,Object> param){
		StringBuilder keys=new StringBuilder();
		Object[] values=new Object[param.keySet().size()];
		int i=0;
		for(String key:param.keySet()){
			keys.append("and _t001.").append(key).append("=?").append(i+1).append(" ");
			values[i]=param.get(key);
			i++;
		}
		String hql="delete "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 "+keys.toString();
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        for(int j=0;j<values.length;j++){
            query.setParameter(j+1,values[j]);
        }
        query.executeUpdate();
	}

	/**
	 * 根据查询组合条件删除实体
	 * 
	 */
	public void deleteEntityByByQueryParam(List<QueryParam> queryParamList){
		String hql="delete from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 " ;
		List<Object> params=new ArrayList<>();
		ParamPos paramPos=new ParamPos(1);
		for(QueryParam queryParam:queryParamList){
			hql=this.genWhereHql(hql, params, queryParam,paramPos);
		}
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        for(int j=0;j<params.size();j++){
            query.setParameter(j+1,params.get(j));
        }
        query.executeUpdate();
	}
	/**
	 * 根据id得到实体
	 */
	public T getEntityById(String id){
        return (T) this.getSessionFactory().getCurrentSession().get(this.getEntityClazz(),id);
	}
	/**
	 * 根据实体属性得到实体列表
	 */
	public List<T> getEntityListByProperty(String propertyName,Object propertyValue,Map<String,String> orderProps){
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001 where _t001."+propertyName+"=?1";
		hql=this.genOrderHql(hql, orderProps);
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(1,propertyValue);
		return query.list();
	}
	/**
	 * 根据实体属性得到记录数
	 */
	public int getEntityCountByProperty(String propertyName,Object propertyValue){
		String hql="select count(_t001) from "+this.getEntityClazz().getCanonicalName()+" _t001 where _t001."+propertyName+"=?1";
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(1, propertyValue);
		return ((Long)query.uniqueResult()).intValue();
	}
	/**
	 * 根据实体属性得到一页记录
	 */
	public List<T> getEntityListByProperty(String propertyName,Object propertyValue,int start, int count,Map<String,String> orderProps){
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001 where _t001."+propertyName+"=?1";
		hql=this.genOrderHql(hql, orderProps);
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(1, propertyValue);
		query.setFirstResult(start);
		query.setMaxResults(count);
		return (List<T>) query.list();
	}

	/**
	 * 根据实体属性键值对得到实体列表
	 */
	public List<T> getEntityListByPropertys(Map<String,Object> param,Map<String,String> orderProps){
		StringBuilder keys=new StringBuilder();
		Object[] values=new Object[param.keySet().size()];
		int i=0;
		for(String key:param.keySet()){
			keys.append("and _t001.").append(key).append("=?").append(i+1).append(" ");
			values[i]=param.get(key);
			i++;
		}
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 "+keys.toString();
		hql=this.genOrderHql(hql, orderProps);
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        for(int j=0;j<values.length;j++){
            query.setParameter(j+1,values[j]);
        }
		return query.list();
	}

	/**
	 * 根据实体属性键值对得到记录数
	 */
	public int getEntityCountByPropertys(Map<String,Object> param){
		StringBuilder keys=new StringBuilder();
		Object[] values=new Object[param.keySet().size()];
		int i=0;
		for(String key:param.keySet()){
			keys.append("and _t001.").append(key).append("=?").append(i+1).append(" ");
			values[i]=param.get(key);
			i++;
		}
		String hql="select count(_t001) from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 "+keys.toString();
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		for(int j=0;j<values.length;j++){
			query.setParameter(j+1, values[j]);
		}
		return ((Long)query.uniqueResult()).intValue();
	}

	/**
	 * 根据实体属性键值对得到一页记录
	 */
	public List<T> getEntityListByPropertys(Map<String,Object> param,int start, int count,Map<String,String> orderProps){
		StringBuilder keys=new StringBuilder();
		Object[] values=new Object[param.keySet().size()];
		int i=0;
		for(String key:param.keySet()){
			keys.append("and _t001.").append(key).append("=?").append(i+1).append(" ");
			values[i]=param.get(key);
			i++;
		}
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 "+keys.toString();
        logger.debug(hql);
		hql=this.genOrderHql(hql, orderProps);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		for(int j=0;j<values.length;j++){
			query.setParameter(j+1, values[j]);
		}
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}


	/**
	 * 得到所有实体集合
	 */
	public List<T> getEntityListAll(Map<String,String> orderProps){
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001";
		hql=this.genOrderHql(hql, orderProps);
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        return query.list();
	}

	/**
	 * 得到所有实体集合记录数
	 */
	public int getEntityCountAll(){
		String hql="select count(_t001) from "+this.getEntityClazz().getCanonicalName()+" _t001";
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		return ((Long)query.uniqueResult()).intValue();
	}
	/**
	 * 得到所有实体集合一页记录
	 */
	public List<T> getEntityListAll(int start, int count , Map<String,String> orderProps){
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001";
		hql=this.genOrderHql(hql, orderProps);
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
	/**
	 * 根据查询组合条件得到实体记录数
	 * 
	 */
	public int getEntityCountByQueryParam(List<QueryParam> queryParamList){
		String hql="select count(_t001) from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 " ;
		List<Object> params=new ArrayList<>();
		ParamPos paramPos=new ParamPos(1);
		for(QueryParam queryParam:queryParamList){
			hql=this.genWhereHql(hql, params, queryParam,paramPos);
		}
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.size();i++){
			query.setParameter(i+1, params.get(i));
		}
		return ((Long)query.uniqueResult()).intValue();
	}
	/**
	 * 根据查询组合条件得到实体记录集合
	 * 
	 */
	public List<T> getEntityListByQueryParam(List<QueryParam> queryParamList,Map<String,String> orderProps){
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 " ;
		List<Object> params=new ArrayList<>();
		ParamPos paramPos=new ParamPos(1);
		for(QueryParam queryParam:queryParamList){
			hql=this.genWhereHql(hql, params, queryParam,paramPos);
		}
		hql=this.genOrderHql(hql,orderProps);
		logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        for(int j=0;j<params.size();j++){
            query.setParameter(j+1,params.get(j));
        }
        return query.list();
	}
	/**
	 * 根据查询组合条件得到一页实体记录集合
	 * 
	 */
	public List<T> getEntityListByQueryParam(List<QueryParam> queryParamList,int start, int count,Map<String,String> orderProps){
		String hql="from "+this.getEntityClazz().getCanonicalName()+" _t001 where 1=1 " ;
		List<Object> params=new ArrayList<>();
		ParamPos paramPos=new ParamPos(1);
		for(QueryParam queryParam:queryParamList){
			hql=this.genWhereHql(hql, params, queryParam,paramPos);
		}
		hql=this.genOrderHql(hql, orderProps);
        logger.debug(hql);
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.size();i++){
			query.setParameter(i+1, params.get(i));
		}
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.list();
	}
}

