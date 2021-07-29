package com.sample.dao;

import java.util.List;

import com.sample.vo.Review;

public interface ReviewDao {
	/**
	 * 새 리뷰정보를 저장합니다.
	 * @param review 새 리뷰정보
	 */
	void insertReview(Review review);
	
	/**
	 * 지정된 번호의 리뷰정보를 삭제합니다.
	 * @param reviewNo 리뷰번호
	 */
	void deleteReview(int reviewNo);
	
	/**
	 * 지정된 상품에 대해 작성한 리뷰정보를 반환합니다.
	 * @param productNo 상품번호
	 * @return 리뷰정보 목록
	 */
	List<Review> getReviewsByProductNo(int productNo);

	/**
	 * 지정된 리뷰번호에 해당하는 리뷰정보를 반환합니다.
	 * @param reviewNo 리뷰번호
	 * @return 리뷰정보
	 */
	Review getReviewByNo(int reviewNo);

	/**
	 * 지정된 사용자가  작성한 리뷰를 반환한다.
	 * @param userId 사용자 아이디
	 * @return 리뷰 목록
	 */
	List<Review> getReviewsByUserId(String userId);
}
