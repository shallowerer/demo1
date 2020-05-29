package com.huaxianvwa.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.huaxianvwa.school.entity.CommodityOrder;

public interface CommodityOrderDAO extends JpaRepository<CommodityOrder, Integer>{

	CommodityOrder findByOrderNo(Integer memberno);

}
