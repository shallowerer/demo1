package com.huaxianvwa.school.controller;
/**
 * @author zsj
 * @date 2020/3
 */

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.dao.AdminRoleDAO;
import com.huaxianvwa.school.entity.AdminPermission;
import com.huaxianvwa.school.entity.AdminRole;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.AdminPermissionService;
import com.huaxianvwa.school.service.AdminRoleMenuService;
import com.huaxianvwa.school.service.AdminRolePermissionService;
import com.huaxianvwa.school.service.AdminRoleService;

/**
 * Role controller.
 *
 * @author Evan
 * @date 2019/11
 */
@RestController
public class RoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    @GetMapping("/api/admin/role")
    @ResponseBody
    public List<AdminRole> listRoles(){
        return adminRoleService.list();
    }

    @PutMapping("/api/admin/role/status")
    @ResponseBody
    public Result updateRoleStatus(@RequestBody AdminRole requestRole) {
        AdminRole adminRole = adminRoleService.updateRoleStatus(requestRole);
        String message = "用户" + adminRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/role")
    @ResponseBody
    public Result editRole(@RequestBody AdminRole requestRole) {
        adminRoleService.addOrUpdate(requestRole);
        adminRolePermissionService.savePermChanges(requestRole.getId(), requestRole.getPerms());
        System.out.println(requestRole);
        System.out.println(adminRoleService.findById(requestRole.getId()));
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }


    @PostMapping("/api/admin/role")
    @ResponseBody
    public Result addRole(@RequestBody AdminRole requestRole) {
        adminRoleService.editRole(requestRole);
        return ResultFactory.buildSuccessResult("操作成功");
    }
    
    @DeleteMapping("/api/admin/deleteRole/{id}")
    @ResponseBody
    public Result deleteRole(@PathVariable("id") Integer id) {
        adminRoleService.delateRoleById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
    
    @GetMapping("/api/admin/role/perm")
    @ResponseBody
    public List<AdminPermission> listPerms() {
        return adminPermissionService.list();
    }

    @PutMapping("/api/admin/role/menu")
    @ResponseBody
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody LinkedHashMap menusIds) {
    	System.out.println(menusIds);
        if(adminRoleMenuService.updateRoleMenu(rid, menusIds)) {
            return ResultFactory.buildSuccessResult("更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }
}
