package com.sample.di.demo6;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class DataBackup {
	
	/*
	 * 의존성 자동 주입하기
	 * 		- @Resource 어노테이션을 사용하면 의존성을 가지는 빈을 자동으로 주입할 수 있다.
	 * 		- @Resource 어노테이션은 기본적으로 어노테이션이 부착된 멤버변수, setter메소드 타입을
	 * 			분석하고, 스프링 컨테이너에서 동일한 타입이나 자식타입의 객체를
	 * 			찾아서 자동으로 의존성 주입을 수행한다.
	 * 		- 동일한 타입의 객체가 2개 이상 발견될 때는 @Primary 어노테이션이 부착된 객체가 
	 * 			의존성 주입된다.
	 * 		- @Resource(name="주입할 빈의 이름")의 name을 확인해서 스프링 컨테이너에 동일한 이름으로
	 * 			등록된 객체를 찾아서 의존성 주입한다.
	 * 			* @Primary 어노테이션 보다 적용 우선 순위가 높다.
	 */
	
	@Resource(name = "fileDataWriter")
	public DataWriter dataWriter;
	
	public void backup(String content) {
		dataWriter.write(content);
	}
}
