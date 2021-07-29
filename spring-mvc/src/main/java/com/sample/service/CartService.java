package com.sample.service;

import java.util.List;

import com.sample.dto.CartItemDto;
import com.sample.vo.CartItem;

public interface CartService {

	/**
	 * 지정된 장바구니 아이템정보를 장바구니에 추가하는 서비스
	 * @param cartItem 장바구니 아이템 정보
	 */
	void addCartItem(CartItem cartItem);
	
	/**
	 * 지정된 사용자의 장바구니 아이템정보를 제공하는 서비스
	 * @param userId 사용자아이디
	 * @return 장바구니 아이템 정보 목록
	 */
	List<CartItemDto> getMyCartItems(String userId);

	/**
	 * 지정된 카트아이템을 삭제하는 서비스
	 * @param itemNo 카트 아이템 번호
	 * @param id 사용자 아이디
	 */
	void removeCartItem(int itemNo, String id);
	
}
