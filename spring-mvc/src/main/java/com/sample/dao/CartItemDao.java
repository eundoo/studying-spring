package com.sample.dao;

import org.apache.ibatis.annotations.Param;

import com.sample.vo.CartItem;

public interface CartItemDao {

	void insertCartItem(CartItem cartItem);
	void updateCartItem(CartItem cartItem);
	CartItem getCartItem(@Param("userId") String userId, @Param("productNo") int productNo);
}
