package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.CartItemDao;
import com.sample.dto.CartItemDto;
import com.sample.exception.SampleException;
import com.sample.vo.CartItem;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired CartItemDao cartItemDao;
	
	@Override
	public void addCartItem(CartItem cartItem) {
		CartItem savedCartItem = cartItemDao.getCartItem(cartItem.getUserId(), cartItem.getProductNo());
		if (savedCartItem == null) {
			cartItemDao.insertCartItem(cartItem);
		} else {
			savedCartItem.setAmount(savedCartItem.getAmount() + 1);
			cartItemDao.updateCartItem(savedCartItem);
		}		
	}
	
	@Override
	public List<CartItemDto> getMyCartItems(String userId) {
		return cartItemDao.getCartItemsByUserId(userId);
	}
	
	@Override
	public void removeCartItem(int itemNo, String userId) {
		CartItem cartItem = cartItemDao.getCartItemByNo(itemNo);
		if (!cartItem.getUserId().equals(userId)) {
			throw new SampleException("사용자 불일치", "본인의 장바구니에 저장된 아이템만 삭제할 수 있습니다.");
		}
		
		cartItemDao.deleteCartItem(itemNo);
	}
}
