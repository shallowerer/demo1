package com.huaxianvwa.school.dao;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

import org.hibernate.criterion.Projection;


import org.springframework.stereotype.Repository;

@Repository("baseDao")
public interface BaseDao{

	
	public <T> T getById(Class<T> c, Number id);

//	public void saveOrUpdate(Object bean);
//
//	public Integer save(Object bean);
//
//	public void update(Object bean);

	public <T> List<T> getList(String sql, Object... arg);

	public <T> List<T> getListByIds(Class<T> c, Integer[] ids);

	public <T> List<T> findByParameter(String sql, Object... args);

	public <T> List<T> findByCriteria(Criteria c);

	public <T> T getEntityByAttribute(Class<T> c, Map<String, Object> fieldMap);

	public <T> void deleteById(Class<T> c, Number id);

	public Object calculateByCriteria(Criteria c, Projection p);

}