package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.model.FinanceModel;

public interface FinanceModelDAO extends JpaRepository<FinanceModel, Integer> {
	
//	-------------------初始静态-------------
	
	//  年月日销售数据(日)
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order group by year(order_time),month(order_time),day(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouru();
	

	// 所有年收入（年）
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order group by year(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouruOfYear();
	
	// 所有月收入（年）
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order group by month(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouruOfMonth();
	
//	--------------------输入查询-------------
	//	按年查询(年) 得月
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order where year(order_time) in (?1) group by year(order_time),month(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouruByYear(Integer yearInteger);
	
	
	
	//	按年月查询(月) 得日
	@Query(value = "select id,year(order_time) year,month(order_time) month,day(order_time) day,sum(total_price) total from commodity_order where year(order_time) in (?1)  and month(order_time) in (?2) group by year(order_time),month(order_time),day(order_time)",nativeQuery = true)
	public List<FinanceModel> allShouruByMonth(Integer yearInteger,Integer monthInteger);
}
