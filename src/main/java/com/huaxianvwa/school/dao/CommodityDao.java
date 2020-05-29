package com.huaxianvwa.school.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.Category;
import com.huaxianvwa.school.entity.Commodity;
import com.huaxianvwa.school.entity.Member;

public interface CommodityDao extends JpaRepository<Commodity, Integer>{
	List<Commodity> findAllByCategory(Category category);
    List<Commodity> findAllByTitleLikeOrIdLike(String keyword1, String keyword2);
    
    
	@Query(value = "from Commodity m where m.title like %?1%")
	List<Commodity> findByTitleLike(String name);
	
	@Query(value = "from Commodity m where m.cno like %?1%")
	List<Commodity> findByCnoLike(Integer cno);
	
	@Query(value = "from Commodity m where m.date like %?1%")
	List<Commodity> findByDateLike(String phone);
}	
