package com.sample.di.demo6;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
 * @Primary
 * 		스프링 컨테이너에 동일한 타입의 객체가 여러 개 존재하는 경우
 * 		@Primary 어노테이션이 부착된 객체가 존재하면 @Autowired어노테이션은 해당 객체를
 * 		의존성 주입에 사용한다.
 */
@Primary
@Component
public class CloudDataWriter implements DataWriter{

	@Override
	public void write(String content) {
		System.out.println("["+content+"]를 클라우드 저장소에 저장합니다.");
		
	}
}
