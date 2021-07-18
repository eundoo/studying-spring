package com.sample.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * @Aspect
 * 		- Aspect를 생성시키게하는 어노테이션이다.
 * 			* Aspect는 Advice와 Pointcut이 결합되어 있는 것이다.
 * 		- 스프링 컨테이너는 @Aspect 어노테이션이 지정된 클래스를 검색해서 Spring AOP 설정에 반영한다.
 * 			* 공통 기능 적용 대상이 되는 빈을 검색해서 Target객체의 Join Point에 
 * 			Aspect가 적용된 Proxy객체를 생성한다.
 */
@Aspect
@Component
public class LoggingAdvice {

	/*
	 * Before Advice의 구현
	 * 		@Before
	 * 			- 공통기능의 실행 싯점을 지정한다.
	 * 			- 결합지점(메소드 실행)의 메소드 실행전에 공통기능이 실행되게 한다.
	 * 		execution
	 * 			- 메소드 실행 결합점에 대한 Advice 적용규직을 작성한다.
	 * 			- 항상 적용대상이 되는 메소드를 직접적으로 표현하는 적용규칙 이어야 한다.
	 * 		*com.sample.service.*.*(..)
	 * 			-*은 반환타입을 표시한다. *은 void를 포함한 모든 반환값에 대응
	 * 			- com.sample.service.*
	 * 				com.sample.service패키지의 모든 클래스를 의미한다.
	 * 			-*(..)은 모든 메소드를 나타낸다.
	 * 
	 * 	JoinPoint 객체
	 * 		* 공통기능이 적용되는 대상 객체, 대상메소드에 대한 정보를 포함하고 있는 객체이다.
	 */
	@Before("execution(* com.sample.service.*.*(..))")
	public void logging(JoinPoint joinPoint) {
		//공통기능이 적용되는 대상객체의 클래스 이름
		String targetClassName = joinPoint.getTarget().getClass().getTypeName();
		//공통기능이 적용되는 대상메소드 이름
		String methodName = joinPoint.getSignature().getName();
		//공통기능이 적용되는 대상메소드 호출시 전달받은 매개변수 값
		Object[] args = joinPoint.getArgs();
		
		System.out.println();
		System.out.println("########################### 로그출력");
		System.out.println("클래스명 : " + targetClassName);
		System.out.println("메소드명 : " + methodName);
		System.out.println("매개변수값 : " + Arrays.toString(args));
	}
}
