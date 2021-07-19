package com.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.service.UserService;
import com.sample.vo.User;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-tx.aop.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = userService.getUserDetail("hong1000");
		System.out.println(user);
		
		User user2 = User.builder()
						.id("hong1512").password("zxcv1234")
						.name("횽길동").email("hong1512").phone("010-1234-5678").build();
		userService.addNewUser(user2);
	}

}
