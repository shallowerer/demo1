package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.CommodityOrder;

public interface CommodityOrderDAO extends JpaRepository<CommodityOrder, Integer>{

	CommodityOrder findByOrderNo(Integer memberno);

	@Query(value = "from CommodityOrder m where m.address like %?1%")
	List<CommodityOrder> findByAddressLike(String address);

	@Query(value = "select * from commodity_order where convert(order_time,datetime) like %?1%",nativeQuery=true)
	List<CommodityOrder> findByOrderTimeLike(String orderTime);

}
