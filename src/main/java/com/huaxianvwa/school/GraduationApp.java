package com.huaxianvwa.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




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
