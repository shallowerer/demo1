package com.huaxianvwa.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.entity.User;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.UserService;

@RestController
public class PersonalContorller {
	@Autowired
	UserService userService;
	
	@PutMapping("/api/personal/setSimpleInfo")
	public Result setSimpleInfo(@RequestBody User requestUser) {
//		 public Result register(@RequestBody User user) {
        int status = userService.setSimpleInfo(requestUser);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("修改成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
	
	@PutMapping("/api/personal/setPassword")
	public Result setPassword(@RequestBody User requestUser) {
		System.out.println(requestUser.getPassword());
		if(userService.setPassword(requestUser)){
			return ResultFactory.buildFailResult("成功");
		};
		return ResultFactory.buildFailResult("未知错误");
   }
}
