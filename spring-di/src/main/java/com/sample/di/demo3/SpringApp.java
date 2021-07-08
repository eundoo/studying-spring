package com.sample.di.demo3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
										// src/main에 있는 package들, 모든 라이브러리들
										// src/resources안에 있는 것들에 접근 가능하다. (온데군데 다 접근가능)
		ApplicationContext container = new ClassPathXmlApplicationContext("context-di-demo3.xml");
		
		EventService eventService = container.getBean("eventService", EventService.class);
		eventService.noticeEvent("여름 휴가 대비하세요", "집 밖은 위험하니까 집콕 할 수 있는 아이템을 받아가세요");
		SystemAlertService alertService = container.getBean("alertService", SystemAlertService.class);
		alertService.alert("안알랴줌", "니 오타겠쥐~~", "이클립스");
	}
}
