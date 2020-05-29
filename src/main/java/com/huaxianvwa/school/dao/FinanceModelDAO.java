package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.model.FinanceModel;

public interface FinanceModelDAO extends JpaRepository<FinanceModel, Integer> {
	
	//  年月日销售数据(按日)
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order group by year(order_time),month(order_time),day(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouru();
	
	//	按年查询
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order where year(order_time) in (?1) group by year(order_time),month(order_time),day(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouruByYear(Integer yearInteger);
	
	//	按年月查询
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order where year(order_time) in (?1)  and month(order_time) in (?2) group by year(order_time),month(order_time),day(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouruByMonth(Integer yearInteger,Integer monthInteger);
}
