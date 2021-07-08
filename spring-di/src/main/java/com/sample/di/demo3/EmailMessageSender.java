package com.sample.di.demo3;

public class EmailMessageSender implements MessageSender{

	private String host;
	private int port;
	private String username;
	private String password;
	
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void sendMessageSender(String title, String content, String from, String to) {
		System.out.println("------- 메일 서버 설정 정보 -------");
		System.out.println("호  스  트:" + host);
		System.out.println("포트 번호:" + port);
		System.out.println("사용자 명:" + username);
		System.out.println("비밀 번호:" + password);
		System.out.println("----------------------------");
		System.out.println("--------- 이메일 발송 ---------");
		System.out.println("보내는 곳:" + from);
		System.out.println("받는    곳:" + to);
		System.out.println("제       목:" + title);
		System.out.println("내       용:" + content);
		System.out.println("----------------------------");
	}
}
