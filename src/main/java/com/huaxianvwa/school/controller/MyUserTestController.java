package com.huaxianvwa.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.dao.UserDao;
import com.huaxianvwa.school.entity.User;
import com.huaxianvwa.school.repository.UserRepository;

/**
 * MyUserTestController controller.
 *
 * @author zsj
 * @date 2020/3/13 12:03
 * @describe 测试控制以及数据库连接
 */
@RestController
@RequestMapping("/user")
public class MyUserTestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/findAll")
	@ResponseBody
	public List<User> findAll(){
		System.out.println(userRepository.findAll());
		return   userRepository.findAll();
		
	}

}
