package com.sample.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sample.exception.MallBusinessException;

@Aspect
@Component
public class AspectUsingAdvice {
	//이거를 app에서 실행하면 @Before > @After > @AfterReturning순으로 실행된다.
	
	//BeforeAdvice
	/*
	 * Before Advice
	 * 		- 대상 메소드 실행 전에 실행되는 advice이다.
	 * 		- @Before 사용
	 * 		- JoinPoint 객체를 매개변수로 제공받을 수 있다.
	 * 		- Advice 적용 대상객체, 대상 메소드, 대상 데소드의 매개변수 등을 조회할 수 있다.
	 * 		- 로그 출력, 인증, 권한확인 등의 작업을 수행할 수 있다.
	 */
	@Before("execution(* com.sample.service.*Service.*(..))")
	public void beforeTargetMethod(JoinPoint joinPoint) {
		System.out.println("beforeTargetMethod() is running!");
	}
	
	/*
	 * After Returning Advice
	 * 		- 대상 메소드가 예외없이 정상적으로 종료되었을 때 실행되는 advice이다.
	 * 		- @AfterReturning 사용
	 * 		- JoinPoint, 대상 메소드의 반환 값을 제공받을 수 있다. 반환값이 있으면 그 반환값, 없으면 null
	 * 		- Advice 적용 대상객체, 대상 메소드, 대상 메소드의 매개변수 등을 조회 할 수 있다.
	 * 		- 대상 메소드가 반환하는 반환값을 조회할 수 있다.(반환값을 변경할 수는 없다.)
	 */
	@AfterReturning(pointcut = "execution(* com.sample.service.*Service.*(..))",
					returning = "returnValue")
												//반환값이 있는 경우에 returnValue로 전달되고 전달될게 없으면 null이 반환됨
	public void afterReturningTargetMethod(JoinPoint joinPoint, Object returnValue) {
		System.out.println("afterReturningTargetMethod() running!");
		System.out.println("대상 메소드가 반환한는 값: " + returnValue);
	}
	
	/*
	 * After throwing Advice
	 * 		- 대상 메소드가 실행 중 예외를 발생시키고 종료되었을 때 실행되는 advice이다.
	 * 		- @AfterThrowing 사용
	 * 		- JoinPoint, 대상 메소드가 발생시킨 예외객체를 제공받을 수 있다.
	 * 		- Advice 적용 대상객체, 대상메소드, 대상 메소드의 매개변수 등을 조회할 수 있다.
	 * 		- 대상 메소드가 발생시킨 예외 정보를 조회할 수 있고, 예외를 사용자정의 예외로 변경할 수 있다.
	 * 		- 애플리케이션 실행과정 시 발생하는 다양한 예외를 사용자 정의 예외로 변환하는 작업에 활용된다. 
	 */
	@AfterThrowing(pointcut = "execution(* com.sample.service.*Service.*(..))",
					throwing = "exception")
	public void afterThrowingTargetMethod(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterThrowingTargetMethod() is running!");
		System.out.println("오류: " + exception);
		System.out.println("오류 메세지: " + exception.getMessage());
		
		//대상 메소드에서 발생한 예외 대신, MallBusinessException예외를 던진다.
		throw new MallBusinessException("내부 서버 에러", exception);
	}
	
	/*
	 * After Advice
	 * 		- 대상 메소드 수행 후 무조건 실행되는 advice이다.
	 * 		- @After 사용
	 * 		- JoinPoint를 제공받을 수 있다.
	 * 		- Advice 적용 대상객체, 대상메소드, 대상 메소드의 매개변수 등을 조회할 수 있다.
	 */
	@After("execution(* com.sample.service.*Service.*(..))")
	public void afterTargetMethod(JoinPoint joinPoint) {
		System.out.println("afterTargetMethod() is running!");
	}
	
	/*
	 * around Advice
	 * 		- 대상 메소드 수행 전/후에 실행되는 advice이다.
	 * 		- @Around 사용
	 * 		- ProceedingJoinPoint를 제공받을 수 있다.
	 * 		- Advice적용 대상 객체, 대상 메소드, 대상 메소드의 매개변수 등을 조회할 수 있다.
	 * 		- 대상 메소드를 직접 실행시킬 수 있다. 대상 메소드의 실행 결과도 획득 할 수 있다.
	 * 		- 대상 메소드의 실행여부를 조정할 수 있고, 대상 메소드의 반환값도 조작이 가능하다.
	 * 		- before, after returning, after throwing, after advice를 전부 흉내낼 수 있다.
	 * 		- 실행시간체크, 트랜잭션처리 등에 활용할 수 있다.
	 */
	
	@Around("execution(* com.sample.service.*Service.*(..))")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//대상 메소드를 실제로 실행시키는 수행문
		Object obj = joinPoint.proceed();
		
		
		return obj;
	}
}
