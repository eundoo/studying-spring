package com.sample.di.demo5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-di-demo5.xml");
		
		UserDao userDao = context.getBean(UserDao.class);
		UserPointHistoryDao userPointHistory = context.getBean(UserPointHistoryDao.class);
		
		userDao.deleteAllUsers();
		userPointHistory.getPointHistory("oh");
	}
}
