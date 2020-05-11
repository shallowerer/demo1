package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.Category;


public interface CategoryDao extends  JpaRepository<Category, Integer> {
	@Query(value = "from Category c where c.name like %?1%")
	List<Category> findByNameLike(String name);
	
	Category findByName(String name);
}
