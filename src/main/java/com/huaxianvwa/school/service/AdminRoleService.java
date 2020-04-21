package com.huaxianvwa.school.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.huaxianvwa.school.dao.AdminRoleDAO;
import com.huaxianvwa.school.entity.AdminMenu;
import com.huaxianvwa.school.entity.AdminPermission;
import com.huaxianvwa.school.entity.AdminRole;
import com.huaxianvwa.school.entity.AdminUserRole;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminMenuService adminMenuService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    
    public List<AdminRole> list() {
        List<AdminRole> roles = adminRoleDAO.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    public AdminRole findById(int id) {
        return adminRoleDAO.findById(id);
    }

    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public List<AdminRole> listRolesByUser(String username) {
        int uid =  userService.findByUsername(username).getId();
        List<AdminRole> roles = new ArrayList<>();
        List<AdminUserRole> urs = adminUserRoleService.listAllByUid(uid);
        for (AdminUserRole ur: urs) {
            roles.add(adminRoleDAO.findById(ur.getRid()));
        }
        return roles;
    }

    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return adminRoleDAO.save(roleInDB);
    }

    public boolean editRole(@RequestBody AdminRole requestRole) {    	
        try {
            adminRoleDAO.save(requestRole);
            adminRolePermissionService.savePermChanges(requestRole.getId(), requestRole.getPerms());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    public boolean delateRoleById(Integer id) {
        try {
        	adminRolePermissionService.deleteAllByRid(id);
        	adminRoleMenuService.deleteAllByRid(id);
            adminRoleDAO.delete(id);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
