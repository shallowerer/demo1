package com.huaxianvwa.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.jpa.repository.JpaRepository;


import com.huaxianvwa.school.entity.Commodity;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

}
