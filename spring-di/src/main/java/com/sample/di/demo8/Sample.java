package com.sample.di.demo8;

import java.util.List;
import java.util.Map;

public class Sample {

	List<String> categories;
	List<Sns> snsList;
	Map<String, Sns> snsMap;

	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	public void setSnsList(List<Sns> snsList) {
		this.snsList = snsList;
	}
	
	public void setSnsMap(Map<String, Sns> snsMap) {
		this.snsMap = snsMap;
	}
	
	public void printCategoies() {
		System.out.println("### List<String> categories에 대한 의존성 주입결과 확인");
		for (String category : categories) {
			System.out.println("카테고리:" + category);
		}
		System.out.println();
	}
	
	public void printSnsList() {
		System.out.println("### List<Sns> snsList에 대한 의존성 주입결과 확인");
		for (Sns sns : snsList) {
			sns.post("의존성 주입 연습", "콜렉션 타입의 의존성 주입 연습");
		}
		System.out.println();
	}
	
	//Map<String, Sns> snsMap <-- {"insta":Instargram객체, "facebook":Facebook객체}
	public void printSnsMap(String key) {
		System.out.println("### Map<String, Sns> snsMap에 대한 의존성 주입결과 확인");
		Sns sns = snsMap.get(key);
		sns.post("의존성 주입 연습", "Map타입의 의존성 주입 연습");
	}
	
}
