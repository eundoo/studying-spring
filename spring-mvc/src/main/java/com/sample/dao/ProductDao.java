package com.sample.dao;

import java.util.List;

import com.sample.vo.Product;

public interface ProductDao {

	/**
	 * 모든 상품정보를 반환한다.
	 * @return 상품정보 목록
	 */
	List<Product> getAllProducts();
	
	Product getProductByNo(int productNo);
}
