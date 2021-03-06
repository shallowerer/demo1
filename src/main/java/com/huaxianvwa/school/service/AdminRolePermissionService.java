package com.huaxianvwa.school.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huaxianvwa.school.dao.AdminRolePermissionDAO;
import com.huaxianvwa.school.entity.AdminPermission;
import com.huaxianvwa.school.entity.AdminRolePermission;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDAO.findAllByRid(rid);
    }
    
    @Transactional
    public void deleteAllByRid(Integer rid){
    	adminRolePermissionDAO.deleteAllByRid(rid);
    }

//    @Modifying
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDAO.deleteAllByRid(rid);
        if(perms != null){
	    	for (AdminPermission perm : perms) {
	            AdminRolePermission rp = new AdminRolePermission();
	            rp.setRid(rid);
	            rp.setPid(perm.getId());
	            adminRolePermissionDAO.save(rp);
	        }
        }else {
        	System.out.println("初设角色暂时没有权限");
		}
    }
}
