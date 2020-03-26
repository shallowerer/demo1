package com.huaxianvwa.school.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.huaxianvwa.school.dao.BaseRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	private final EntityManager entityManager;
	
    protected HibernateTemplate		hibernateTemplate;
	protected TransactionTemplate	transactionTemplate;

    //父类没有不带参数的构造方法，这里手动构造父类
    public BaseRepositoryImpl(Class<T> domainClass,EntityManager entityManager){
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }
    //通过EntityManager来完成查询,指定sql来查询
    @SuppressWarnings("unchecked")
	@Override
    public List<T> listBySql(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }
    
    


	public <T> T getById(Class<T> c, Number id) {
		System.out.println("hibernateTemplate");
		return hibernateTemplate.get(c, id);
	}

	/**
	 * 保存或更新Bean,不带返回值
	 * 
	 * @param bean
	 */

//	public void saveOrUpdate(Object bean) {
//		hibernateTemplate.saveOrUpdate(bean);
//	}
//
//	/**
//	 * 返回新增对象的ID记录
//	 * 
//	 * @param bean
//	 * @return
//	 */
//	public Integer save(Object bean) {
//		return Integer.parseInt(hibernateTemplate.save(bean).toString());
//	}
//
//	/**
//	 * TODO:
//	 */
//	public void update(Object bean) {
//		final Object obj = bean;
//		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				try {
//					hibernateTemplate.update(obj);
//				}
//				catch (Throwable e) {
//					transactionStatus.setRollbackOnly();
//				}
//			}
//		});
//	}
	/**
	 * 根据类的字节码和查询条件获取列表
	 * 
	 * @param c
	 * @param sql
	 * @param arg
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public <T> List<T> getList(String sql, Object... arg) {
		return (List<T>) hibernateTemplate.find(sql, arg);
	}
	/**
	 * TODO:固定参数分页查找
	 * 
	 * @param c
	 * @param sql
	 * @param parameters
	 * @param pager
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> findByParameter(String sql,Object ...args) {
		StringBuffer sb = new StringBuffer().append("select count(*) ").append(sql);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(sql);
		Query countQuery = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(sb.toString());
		int index = 0;
		for (Object o : args) {
			countQuery.setParameter(index, o);
			q.setParameter(index, o);
			index++;
		}
		return q.setFirstResult(index).list();
	}

	/**
	 * TODO:返回一个ID数组的对象
	 */
	public <T> List<T> getListByIds(Class<T> c, Integer[] ids) {  
		List<T> tmpList = new ArrayList<T>();
		for (Integer id : ids) {
			T t = hibernateTemplate.get(c, id);
			tmpList.add(t);
		}
		return tmpList;
	}

	/**
	 * TODO:根据某个类的字段及值获取其对象
	 * 
	 * @param c
	 * @param fieldMap<String fieldName,Object fieldValue>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getEntityByAttribute(Class<T> c, Map<String, Object> fieldMap) {
		String fieldName = fieldMap.keySet().iterator().next();
		Object fieldValue = fieldMap.get(fieldName);
		List<T> entityList = (List<T>) hibernateTemplate.find("from " + c.getSimpleName() + " tb where tb." + fieldName + "=?", fieldValue);
		return entityList.isEmpty() ? null : entityList.get(0);
	}

	/**
	 * TODO:根据动态条件分页查找
	 * 
	 * @param pager
	 * @param ctr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(Criteria c) {
		return (List<T>) c.setProjection(null).setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
	}

	/**
	 * TODO:根据调价c和聚集函数统计.
	 */
	public Object calculateByCriteria(Criteria c, Projection p) {
		Object retVal = c.setProjection(p).uniqueResult();
		return retVal == null ? 0 : retVal;
	}

	/**
	 * TODO:根据ID来删除
	 */
	public <T> void deleteById(Class<T> c, Number id) { 
		final Object obj = hibernateTemplate.get(c, id);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				try {
					hibernateTemplate.delete(obj);
				}
				catch (Throwable e) {
					transactionStatus.setRollbackOnly();
				}
			}
		});
	}

//	public HibernateTemplate getHibernateTemplate() {
//		return hibernateTemplate;
//	}
//
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

}
