package com.sample.service;

import java.util.List;

import com.sample.vo.CartItem;
import com.sample.vo.Product;

/**
 * 상품과 관련된 업무로직 메소드가 정의된 인터페이스다.<br />
 * 여기 정의된 모든 기능은 ProductServiceImpl에서 구현한다.
 * @author lee_e
 *
 */
public interface ProductService {

	/**
	 * 판매중인 모든 상품정보를 제공하는 서비스
	 * @return 상품정보 목록
	 */
	List<Product> getAllProducts();
	
	Product getProductDetail(int productNo);
	
	/**
	 * 지정된 장바구니 아이템정보를 장바구니에 추가하는 서비스
	 * @param cartItem 장바구니 아이템 정보
	 */
	void addCartItem(CartItem cartItem);
}
