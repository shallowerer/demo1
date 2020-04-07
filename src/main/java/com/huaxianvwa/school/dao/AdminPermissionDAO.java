package com.huaxianvwa.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.AdminPermission;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);
}
