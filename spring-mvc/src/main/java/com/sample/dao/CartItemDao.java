package com.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sample.dto.CartItemDto;
import com.sample.vo.CartItem;

public interface CartItemDao {

	void insertCartItem(CartItem cartItem);
	void updateCartItem(CartItem cartItem);
	void deleteCartItem(int itemNo);
	CartItem getCartItemByNo(int itemNo);
	CartItem getCartItem(@Param("userId") String userId, @Param("productNo") int productNo);
	List<CartItemDto> getCartItemsByUserId(String userId);
}
