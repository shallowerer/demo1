package com.huaxianvwa.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import com.huaxianvwa.school.entity.User;

import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.AdminUserRoleService;
import com.huaxianvwa.school.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    


    @GetMapping("/api/admin/user")
    @ResponseBody   
    public List<User> listUsers() {
    	System.out.println("844555555555555555555555555555555555555555555555555555555555555554");
        return userService.list();
    }

    @PutMapping("/api/admin/user/status")
    @ResponseBody
    public Result updateUserStatus(@RequestBody User requestUser) {
        if (userService.updateUserStatus(requestUser)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

    @PutMapping("/api/admin/user/password")
    @ResponseBody
    public Result resetPassword(@RequestBody User requestUser) {
        if (userService.resetPassword(requestUser)) {
            return ResultFactory.buildSuccessResult("重置密码成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，重置失败");
        }
    }

    @PutMapping("/api/admin/user")
    @ResponseBody
    public Result editUser(@RequestBody User requestUser) {
        if(userService.editUser(requestUser)) {
            return ResultFactory.buildSuccessResult("修改用户信息成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，修改失败");
        }
    }
    
    @DeleteMapping("/api/admin/deleteUser/{id}")
    @ResponseBody
    public Result deleteRole(@PathVariable("id") Integer id) {
    	userService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
    
}