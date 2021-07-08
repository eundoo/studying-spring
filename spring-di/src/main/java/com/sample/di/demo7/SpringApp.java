package com.sample.di.demo7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-di-demo7.xml");
		
		EmployeeDao dao = context.getBean(EmployeeDao.class);
		dao.config();
	}
}
