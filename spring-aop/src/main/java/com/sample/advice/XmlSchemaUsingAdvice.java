package com.sample.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.sample.exception.MallBusinessException;

public class XmlSchemaUsingAdvice {

	public void beforeTargetMethod(JoinPoint joinPoint) {
		System.out.println("beforeTargetMethod() is running!");
	}
	
	public void afterReturningTargetMethod(JoinPoint joinPoint, Object returnValue) {
		System.out.println("afterReturningTargetMethod() running!");
		System.out.println("대상 메소드가 반환한는 값: " + returnValue);
	}
	
	public void afterThrowingTargetMethod(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterThrowingTargetMethod() is running!");
		System.out.println("오류: " + exception);
		System.out.println("오류 메세지: " + exception.getMessage());
		
		//대상 메소드에서 발생한 예외 대신, MallBusinessException예외를 던진다.
		throw new MallBusinessException("내부 서버 에러", exception);
	}

	public void afterTargetMethod(JoinPoint joinPoint) {
		System.out.println("afterTargetMethod() is running!");
	}
	
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object value = null;
		
		try {
			System.out.println("aroundTargetMethod() is running! - 대상 메소드 실행 전 실행" );
			value = joinPoint.proceed();	//여기가 핵심이다.!!
			System.out.println("aroundTargetMethod() is running! - 대상 메소드 실행 후 실행" );
			
			return value;
			
		} catch (Throwable cause) {
			System.out.println("aroundTargetMethod() is running! - 대상 메소드 오류 발생 후 실행");
			throw cause;
		} finally {
			System.out.println("aroundTargetMethod() is running! - 대상 메소드  실행 후 무조건 실행");
		}
		
	}
}
