package com.sample.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.service.ProductService;
import com.sample.service.UserService;
import com.sample.vo.Product;
import com.sample.vo.User;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-mybatis.xml");
	
//		ProductService service = context.getBean(ProductService.class);
//		
//		List<Product> products = service.getAllProductList();
//		for(Product product : products) {
//			System.out.println(product.getName() + ", " + product.getMaker());
//		}
		
		UserService userService = context.getBean(UserService.class);
		
		User user  = new User();
		user.setId("hong1000");
		user.setName("홍길동");
		user.setPassword("zxcv1234");
		user.setEmail("hong1000@gmail.com");
		user.setPhone("010-1234-5678");
		
		userService.addNewUser(user);
	}
}
