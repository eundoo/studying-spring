package com.sample.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.service.ProductService;
import com.sample.service.UserService;
import com.sample.vo.Product;
import com.sample.vo.User;

public class SpringApp2 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-aop-schema.xml");
		
		UserService userService = context.getBean(UserService.class);
		ProductService productService = context.getBean(ProductService.class);
		
		System.out.println("### 모든 상품 정보 조회");
		List<Product> products = productService.getAllProductList();
		System.out.println();
		
		System.out.println("### 상품 상세정보 조회");
		Product product = productService.getProductDetail(31);
		System.out.println();
		
		System.out.println("### 사용자 정보 조회");
		User user = userService.getUserDetail("hong1000");
		System.out.println();
	}
}
