package com.huaxianvwa.daoTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.huaxianvwa.school.GraduationApp;
import com.huaxianvwa.school.dao.UserDao;
@RunWith(SpringRunner.class)
@SpringBootTest(classes =GraduationApp.class)
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	

	@Test
	public void findAll(){
		System.out.println("测试成功");
		System.out.println(userDao.findAll());

	}
}
