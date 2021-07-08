package com.sample.di.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		
		ApplicationContext container = new ClassPathXmlApplicationContext("context-di-demo2.xml");
	
		//service를 가져온다.
		OrderService service = container.getBean("orderService", OrderService.class);
		service.order();
		service.cancel();
		
		//service를 가져온다.
		CustomerService customerService = container.getBean("customerService", CustomerService.class);
		customerService.getMyInfo();
		customerService.getMyOrderList();
	}
}
