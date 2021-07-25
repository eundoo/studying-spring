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
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-aop.xml");
		
		ProductService productService = context.getBean(ProductService.class);
		UserService userService = context.getBean(UserService.class);
		
		System.out.println("### 모든 상품정보 조회");
		List<Product> products = productService.getAllProductList();
		System.out.println();
		
		System.out.println("### 31번 상품의 재고를 변경");
		productService.addProduct(310, 100);
		System.out.println();
		
		System.out.println("### 31번 상품의 정보 조회");
		Product product = productService.getProductDetail(31);
		System.out.println();
		
		System.out.println("### 홍길동 고객의 정보 조회");
		User user = userService.getUserDetail("hong");
		System.out.println();
		
		System.out.println("### hong1212 사용자 삭제");
		userService.removeUser("hong1212");
		System.out.println();
	}
}
