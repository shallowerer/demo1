package com.huaxianvwa.school.service;


import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huaxianvwa.school.dao.AdminRoleMenuDAO;
import com.huaxianvwa.school.entity.AdminRoleMenu;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRoleMenuService {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDAO.findAllByRid(rid);
    }

    @Modifying
    @Transactional
    public void deleteAllByRid(int rid) {
        adminRoleMenuDAO.deleteAllByRid(rid);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDAO.save(rm);
    }

    @Modifying
    @Transactional
    public boolean updateRoleMenu(int rid, LinkedHashMap menusIds) {
        try {
            deleteAllByRid(rid);
            for (Object value : menusIds.values()) {
                for (int mid : (List<Integer>) value) {
                    AdminRoleMenu rm = new AdminRoleMenu();
                    rm.setRid(rid);
                    rm.setMid(mid);
                    adminRoleMenuDAO.save(rm);
                }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
