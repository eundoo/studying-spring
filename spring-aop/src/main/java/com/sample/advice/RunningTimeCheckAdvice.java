package com.sample.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.sample.exception.MallBusinessException;

@Aspect
//@Component
public class RunningTimeCheckAdvice {
	/*
	 * ProceedingJoinPoint 객체
	 * 		- 공통기능이 적용되는 대상 객체, 대상 메소드에 대한 정보를 포함하고 있는 객체이다.
	 * 		- 공통기는이 적용되는 대상 메소드를 실행할 수 있는 기능을 가지고 있는 객체이다.
	 * 
	 * @Around Advice
	 * 		- Around advice는 advice내부에서 대상 메소드를 실행하는 수행문을 작성한다.
	 * 		- Around advice는 반드시 대상 메소드 실행 후 획득한 값을 반환해야 한다.(대상 메소드의 반환값이 void인 경우 null이 반환된다.)
	 *		- Around advice는 대상메소드를 실행하는 수행문의 전/후, catch블록, finally블록에서 각각 수행문을 작성해서 
	 *			대상 메소드의 실행 전/후, 예외 발생시에 공통기능을 수행할 수 있다.
	 *		- Around advice는 트랜잭션 처리, 실행시간 체크 등의 공통기능 구현에 주로 사용된다.
	 */
	
	/*
	 * @Around는 Before After다 구현할 수 있다.
	 */
	@Around("within(com.sample.service.*Service)")
	public Object runningTimeCheck(ProceedingJoinPoint joinPoint) {
		
		StopWatch stopWatch = null;
		try {
			//대상 메소드 실행 전 수행할 수행몬 - Before Advice
			stopWatch = new StopWatch();
			stopWatch.start();
			
			//UserServiec, ProductService의 메소드를 실행시킨다.
			Object returnValue = joinPoint.proceed();	//대상 메소드를 실행하는 수행문 여기가 기준이다!!
			//대상 메소드 정상 종료 후 수행할 수행문 - After Returning Advice
			//대상 메소드가 정상적으로 완료되면 이 부분에서 커밋을 한다.
			return returnValue;
		} catch (Throwable cause) {
			//대상 메소드에서 예외 발생 후 수행할 수행문 - After Throwing Advice
			//대상 메소드가 문제가 있어서 여기로 오면 롤백
			throw new MallBusinessException("오류 발생", cause);
		} finally {
			//대상 메소드가 실행된 후 수행할 수행문 - After Advice
			stopWatch.stop();
			long runningTime = stopWatch.getTotalTimeMillis();
			System.out.println("메소드실행 시간: " + runningTime + "밀리초");
		}
	}
}
