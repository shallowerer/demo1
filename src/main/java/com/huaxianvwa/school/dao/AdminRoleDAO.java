package com.huaxianvwa.school.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.AdminRole;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
}
