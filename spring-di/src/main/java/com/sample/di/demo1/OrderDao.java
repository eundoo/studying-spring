package com.sample.di.demo1;

public class OrderDao {

	public void insertOrder() {
		System.out.println("주문정보를 반환합니다.");
	}
	
	public void cancelOrder() {
		System.out.println("주문을 취소합니다.");
	}
	
	public void getOrderListByUserId() {
		System.out.println("주문내역을 조회합니다.");
	}
}
