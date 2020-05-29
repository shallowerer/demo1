package com.huaxianvwa.school.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.AdminUserRole;

/**
 * @author zsj
 * @date 2020/3
 */
public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}
