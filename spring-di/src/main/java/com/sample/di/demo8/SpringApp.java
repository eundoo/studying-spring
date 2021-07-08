package com.sample.di.demo8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-di-demo8.xml");
		
		Sample sample = context.getBean(Sample.class);
		sample.printCategoies();
		sample.printSnsList();
		sample.printSnsMap("insta");
	}
}
