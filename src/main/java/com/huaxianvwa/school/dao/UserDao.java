package com.huaxianvwa.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.huaxianvwa.school.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
}
	
