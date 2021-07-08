package com.sample.di.demo6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-di-demo6.xml");

		DataSaver saver = context.getBean(DataSaver.class);
		saver.save("내용, 내용, 내용, 내용");
		
		DataBackup backup = context.getBean(DataBackup.class);
		backup.backup("백업하자");
	}
}
