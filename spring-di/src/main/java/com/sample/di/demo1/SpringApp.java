package com.sample.di.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext container = new ClassPathXmlApplicationContext("context-di-demo1");
	
		//Dao를 가져오는 3가지 방법
		BookDao bookDao = container.getBean("bookDao", BookDao.class);
		CustomerDao customerDao = container.getBean(CustomerDao.class); //아이디를 적지 않아도 된다.
		OrderDao orderDao = (OrderDao) container.getBean("orderDao");	//object타입으로 나오기 때문에  형변환 필요
	
		bookDao.getAllBooks();
		customerDao.getCustomerById();
		orderDao.cancelOrder();
	}
}
