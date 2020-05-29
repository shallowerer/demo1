package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.PreferenceModel;

public interface PreferenceModelDAO extends JpaRepository<PreferenceModel, Integer>{
	//偏好
//	@Query(value = "select z.id as item_id,count(*) as times, z.commodity_name, z.commodity_id,z.price from order_item z where  order_id in ( select id from commodity_order where mid = ?1 ) group by commodity_id  ",nativeQuery = true)
	@Query(value = "select z.id as item_id, count(*) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z  where order_id in ( select id from commodity_order where mid = ?1 ) group by commodity_id   ",nativeQuery = true)
	public List<PreferenceModel> getPreference(Integer mid);
}
