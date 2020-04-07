package com.huaxianvwa.school.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.huaxianvwa.school.entity.Category;
import com.huaxianvwa.school.entity.Commodity;

public interface CommodityDao extends JpaRepository<Commodity, Integer>{
	List<Commodity> findAllByCategory(Category category);
    List<Commodity> findAllByTitleLikeOrIdLike(String keyword1, String keyword2);
}	
