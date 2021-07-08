package com.sample.di.demo3;

public class EventService {

	//MessageSender인터페이스를 구현한 객체를 의존성 주입으로 전달받아서 저장하는 변수
	private MessageSender messageSender;
	
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	
	//이벤트 공지 기능
	public void noticeEvent(String title, String content) {
		
		messageSender.sendMessageSender(title, content, "홍보팀", "전체고객");
	}
}
