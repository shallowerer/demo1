package com.huaxianvwa.school.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.AdminRoleMenu;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {
    List<AdminRoleMenu> findAllByRid(Integer rid);
    void deleteAllByRid(Integer rid);
}
