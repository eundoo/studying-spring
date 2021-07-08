package com.sample.di.demo5;

import org.springframework.stereotype.Repository;

/*
 * @Repository
 * 		- 데이터테이스와 관련된 작업을 수행하는 클래스에 부착하는 어노테이션이다.
 * 		- <context:component-scan /> 태그를 사용하면 자동으로 스프링의 빈으로 등록되게 한다.
 * 		- 빈의 이름을 따로 지정하지 않으면 클래스 이름으로 스프링 컨테이너에서 관리된다.
 * 		-@Repository("식별자 이름")로 적으면 빈의 이름을 별도로 지정할 수 있다.
 */
@Repository("demo5.UserDao")
public class UserDao {

	public void deleteAllUsers() {
		System.out.println("모든 사용자 정보를 삭제합니다.");

	}
}
