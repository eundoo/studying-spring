package com.sample.mapper;

import java.util.List;

import com.sample.vo.Product;

public interface ProductMapper {
	//Dao대신에 Mapper interface를 만듬
	
	/**
	 * 새 상품정보를 전달받아서 테이블에 추가한다.
	 * @param product 새 상품정보
	 */
	void insertProduct(Product product);

	/**
	 * 변경된 정보가 포함된 상품정보를 전달받아서 테이블에 반영한다.
	 * @param product 변경된 정보를 포함하는 상품정보
	 */
	void updateProduct(Product product);
	
	/**
	 * 모든 상품정보를 테이블에서 조회해서 반환한다.
	 * @return 모든 상품정보 리스트
	 */
	List<Product> getAllProducts();
	
	/**
	 * 상품정보를 전달받아서 그 번호에 해당하는 상품정보를 반환한다.
	 * @param productNo 조회할 상품번호
	 * @return	조회된 상품정보
	 */
	Product getProductByNo(int productNo);
}
