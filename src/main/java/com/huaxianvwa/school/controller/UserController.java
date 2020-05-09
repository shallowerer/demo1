package com.huaxianvwa.school.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityAuthorizeMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.dao.UserDao;
import com.huaxianvwa.school.entity.User;

import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.AdminUserRoleService;
import com.huaxianvwa.school.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    


    @GetMapping("/api/admin/user")  
    public List<User> listUsers() {
    	System.out.println("844555555555555555555555555555555555555555555555555555555555555554");
        return userService.list();
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody User requestUser) {
        if (userService.updateUserStatus(requestUser)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody User requestUser) {
        if (userService.resetPassword(requestUser)) {
            return ResultFactory.buildSuccessResult("重置密码成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，重置失败");
        }
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody User requestUser) {
        if(userService.editUser(requestUser)) {
            return ResultFactory.buildSuccessResult("修改用户信息成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，修改失败");
        }
    }
    
    @DeleteMapping("/api/admin/deleteUser/{id}")
    public Result deleteRole(@PathVariable("id") Integer id) {
    	userService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
    
    @GetMapping("/api/personalInfo")
    public Map<String,Object> personalInfo(Integer id, HttpSession session){
    	Map<String,Object> user = new HashMap<String,Object>();
    	if(session.getAttribute("id") != null){
    		Integer curId = (Integer) session.getAttribute("id");
        	System.out.println(session.getAttribute("id"));
        	user.put("personalInfo",userService.getById(curId));
        	return user;
    	}
    	return user;

    }
    
}
