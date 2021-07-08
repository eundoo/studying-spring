package com.sample.di.demo3;

public class SystemAlertService {

	private MessageSender messageSender;
	
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	
	public void alert(String errorType, String errorMessage, String programName) {
		
		//MessageSender 인터페이스를 구현한 객체를 주입받아서 system 장애내용을
		//관리자에게 발송한다.
		//1. MessageSender 인터페이스를 구현한 객체를 주입받아서 저장하는 멤버변수 정의하기
		//2. 의존성 주입에 사용되는 setter메소드 정의하기
		//3. alert()메소드 구현하기
		//		MessageSender 멤버변수에 주입된 객체의 senderMessage() 메소드를 실행해서
		//		시스템 장애내용을 관리자에게 발송시키는 코드구현
		//		title은 errorType, content는 errorMessage, from은 programName, to는 관리자로 한다.
		messageSender.sendMessageSender(errorType, errorMessage, programName, "관리자");
		//4. context-di-demo3.xml에 SystemAlertService를 스프링의 빈으로 등록하기
		//5. SystemAlertService의 messageSender에 KakaoMessageSender를 주입한다.
		//6. Spring App에서 스프링 컨테이너에서 SystemAlertService객체를 찾아서 시스템 장애 내용을 발송한다.
	}
}
