package com.huaxianvwa.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
