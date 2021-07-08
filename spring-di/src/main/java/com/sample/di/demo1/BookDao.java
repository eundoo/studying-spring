package com.sample.di.demo1;

public class BookDao {

	public void insertBook() {
		System.out.println("새로운 책 정보를 추가합니다.");
	}
	
	public void getAllBooks() {
		System.out.println("모든 책 정보를 반환한다.");
	}
	
	public void getBookDetail() {
		System.out.println("책 상세정보를 조회합니다.");
	}
	
	public void updateBook() {
		System.out.println("책 정보를 변경합니다.");
	}
}
