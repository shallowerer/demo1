package com.huaxianvwa.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.huaxianvwa.school.entity.Category;

public interface CategoryDao extends  JpaRepository<Category, Integer> {

}
