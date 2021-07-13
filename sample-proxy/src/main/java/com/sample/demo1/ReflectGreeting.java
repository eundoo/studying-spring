package com.sample.demo1;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectGreeting {

	public static void main(String[] args) throws Exception {
		// Greeting 객체 생성
		Greeting greeting = new Greeting();
		
		// 참조변수.getClass()를 실행하면 생성된 객체의 설계도 정보(클래스정보)를 가지고 있는 Class 타입의 객체를 반환한다.
		Class<? extends Greeting> clazz = greeting.getClass();
		
		// getTypeName()는 생성된 객체의 전체 클래스이름(패키지 경로를 포함하는)을 반환한다.
		String typeName = clazz.getTypeName();
		System.out.println("클래스 전체이름: " + typeName);
		
		// getDeclaredMethods()는 메소드 정보를 포함하고 있는 Method객체를 메소드의 갯수만큼 생성해서 배열로 반환한다. 
		Method[] methods = clazz.getDeclaredMethods();
		
		System.out.println("------------------------------------------------");
		for (Method method : methods) {
			// getMedifiers()는 메소드의 접근제한자를 구분하는 정수값을 반환한다.
			int modifier = method.getModifiers();
			// getReturnType()는 메소드의 반환값에 대한 반환타입을 반환한다.
			Class<?> returnType = method.getReturnType();
			// getName()은 메소드의 이름을 반환한다.
			String methodName = method.getName();
			// getParameters()는 메소드의 매개변수 정보를 포함하고 있는 Parameter객체를 매개변수 갯수만큼 생성해서 배열로 반환한다.
			Parameter[] parameters = method.getParameters();

			System.out.println("접근제한자: " + modifier);
			System.out.println("리턴타입: " + returnType);
			System.out.println("메소드명: " + methodName);
			
			for (Parameter param : parameters) {
				Class<?> parameterType =  param.getType();
				String parameterName =  param.getName();
				System.out.println("파라미터타입: " + parameterType);
				System.out.println("파라미터명: " + parameterName);
			}
			System.out.println("-------------------------------------------");
			System.out.print("메소드 실행: ");
			
			// Method객체의 invoke(객체, 매개변수값)을 실행하면 지정된 객체에서 해당 Method객체가 표현하는 메소드를 실행할 수 있다.
			method.invoke(greeting, "홍길동");
			System.out.println("-------------------------------------------\n");
		}
	}
}
