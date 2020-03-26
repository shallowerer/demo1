package com.huaxianvwa.school.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 通用repository
 * 我们使用它来简化我们的一些repository的通用CRUD
 * 不需要在每一个repository中写CRUD,只需在需要的repository上继承就好。
 * @NoRepositoryBean，这个表示该接口不会创建这个接口的实例，像UserInfoRepository等，
 * 只要是在jpaConfig里配置的基础名里的接口全会被实例化。
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID>{
    //自定义sql查询
    List<T> listBySql(String sql);
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