package com.sample.service;

import java.util.List;

import com.sample.vo.Product;
import com.sample.vo.Review;

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
	
	/**
	 * 지정된 상품번호에 해당하는 상품의 상세정보를 제공하는 서비스
	 * @param productNo 상품번호
	 * @return 상품정보
	 */
	Product getProductDetail(int productNo);
	
	/**
	 * 상품에 새 리뷰를 등록하는 서비스
	 * @param review 리뷰정보
	 */
	void addReview(Review review);

	/**
	 * 지정된 리뷰번호에 해당하는 리뷰정보를 삭제하는 서비스
	 * @param reviewNo 리뷰번호
	 * @param userId 사용자아이디
	 */
	void removeReview(int reviewNo, String userId);
	
}
