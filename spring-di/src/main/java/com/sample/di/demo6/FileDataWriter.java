package com.sample.di.demo6;

import org.springframework.stereotype.Component;

@Component
public class FileDataWriter implements DataWriter{

	@Override
	public void write(String content) {
		System.out.println("["+content+"]를 파일에 기록합니다.");
		
	}
}
