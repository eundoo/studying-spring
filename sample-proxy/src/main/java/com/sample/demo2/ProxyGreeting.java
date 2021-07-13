package com.sample.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyGreeting {

	public static void main(String[] args) {
		
		// 프록시 객체의 각 메소드가 실행될 때 마다 실행할 작업을 정의한다.
		InvocationHandler invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				if (method.getName().equals("hello")) {
					System.out.println("반갑습니다., " + args[0] + "님");
				} else if (method.getName().equals("bye")){
					System.out.println("안녕히가세요, " + args[0] + "님");
				} else if (method.getName().equals("toString")) {
					return proxy.getClass().getTypeName();
				}
				return null;
			}
		};
		
		// 프록시 객체 생성하기
		// greeting에는 Greeting인터페이스의 각 메소드가 실행될 때마다 실행할 코드
		// (InvocationHandler의 invoke() 메소드에서 재정의한 코드)가 포함된 Proxy객체가 저장되어 있다.
		Greeting greeting = (Greeting) Proxy.newProxyInstance(Greeting.class.getClassLoader(), new Class[] {Greeting.class}, invocationHandler);
		
		System.out.println("Greeting 인터페이스를 구현한 Proxy 객체: " + greeting.toString());
		// greeting에서 .hello()나 .bye() 메소드를 실행하면 
		// InvocationHandler의 invoke() 메소드에서 구현한 내용이 실행된다.
		greeting.hello("김유신");
		greeting.bye("강감찬");
	}
}
