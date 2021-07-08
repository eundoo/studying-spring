package com.sample.di.demo6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSaver {
	
	/*
	 * 의존성 자동 주입
	 * 		@Autowired, @Resource 어노테이션을 사용
	 * 			- 멤버변수, setter메소드, 생성자 메소드에서 사용할 수 있다.
	 * 			- 멤버변수의 타입, setter메소드의 매개변수타입, 생성자 메소드의 매개변수 타입을 
	 * 		   	    분석하고 스프링 컨테이너에 등록된 객체 중에서 동일한 타입이나 자식타입의 객체를
	 * 			    찾아서 자동으로 의존성 주입을 수행한다. 
	 * 			- 어노테이션이 부착된 곳의 타입과 같은 타입 혹은 자식 타입의 객체가 
	 * 			    스프링 컨테이너에 존재하지 않거나, 2개 이상 존재하면 오류가 발생한다.
	 */
	@Autowired
	private DataWriter dataWriter;
	
	public void setDataWriter(DataWriter dataWriter) {
		this.dataWriter = dataWriter;
	}
	
	public void save(String content) {
		dataWriter.write(content);
		
	}
}
