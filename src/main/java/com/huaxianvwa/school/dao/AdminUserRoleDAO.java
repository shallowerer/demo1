package com.huaxianvwa.school.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.AdminUserRole;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}
