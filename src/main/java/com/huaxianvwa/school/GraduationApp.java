package com.huaxianvwa.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxianvwa.school.Factory.BaseRepositoryFactoryBean;



/**
 * 我们使用通用repository时
 * 我们需要让spring在加载的时候找到我们自定义的BaseRepositoryFactoryBean的工厂，
 * 只要在入口类中加入@EnableJpaRepositories即可，代码如下
 */
@EnableJpaRepositories(basePackages = {"com.huaxianvwa.school.repository"},
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)//我们自己的工厂

@SpringBootApplication
@Controller
public class GraduationApp {


	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "你好,世界！";	
	}


	public static void main(String[] args){
		SpringApplication.run(GraduationApp.class, args);
		
	}
}
