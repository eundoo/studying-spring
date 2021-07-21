package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.CartItemDao;
import com.sample.dao.ProductDao;
import com.sample.vo.CartItem;
import com.sample.vo.Product;

/**
 * 상품정보관련 업무로직 메소드를 전부 구현하고 있는 구현 클래스다.<br />
 * @author lee_e
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	/*
	 * ProductDao 매퍼 인터페이스를 구현한 매퍼 인스턴스(객체)가 주입된다.
	 */
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartItemDao cartItemDao;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	@Override
	public Product getProductDetail(int productNo) {
		return productDao.getProductByNo(productNo);
	}
	
	@Override
	public void addCartItem(CartItem cartItem) {
		CartItem savedCartItem = cartItemDao.getCartItem(cartItem.getUserId(), cartItem.getProductNo());
		if(savedCartItem == null) {
			cartItemDao.insertCartItem(cartItem);
		} else {
			savedCartItem.setAmount(savedCartItem.getAmount() + 1);
			cartItemDao.updateCartItem(savedCartItem);
		}
	}
}
