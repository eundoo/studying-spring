package com.sample.dao;

import com.sample.vo.Order;
import com.sample.vo.OrderItem;

public interface OrderDao {

	/**
	 * 지정된 주문정보를 저장합니다.
	 * @param order 주문정보
	 */
	void insertOrder(Order order);
	
	/**
	 * 지정된 주문상품정보를 저장합니다.
	 * @param orderItem 주문상품정보
	 */
	void insertOrderItem(OrderItem orderItem);
	
	/**
	 * 변경된 주문정보를 db에 반영시킨다.
	 * @param order 주문정보
	 */
	void updateOrder(Order order);
}
