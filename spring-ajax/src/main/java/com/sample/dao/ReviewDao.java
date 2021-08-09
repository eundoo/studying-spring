package com.sample.dao;

import java.util.List;

import com.sample.vo.Review;

public interface ReviewDao {

	void insertReview(Review review);
	List<Review> getReviews(int productNo);
	Review getReview(int no);
	void deleteReview(int no);
}
