package com.huaxianvwa.school.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.AdminMenu;

import java.util.List;

/**
 * @author Evan
 * @date 2020/1/10
 */
public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
     AdminMenu findById(int id);
     List<AdminMenu> findAllByParentId(int parentId);
}

