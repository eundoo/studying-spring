package com.sample.service;

import java.util.List;

import com.sample.vo.Product;
import com.sample.vo.Review;

public interface ProductService {

	List<String> getAllCategories();
	List<Product> getProductsByCategory(String category);
	Product getProductDetail(int productNo);
	
	List<Review> getReviewsByProductNo(int productNo);
	void addNewReview(Review review);
	void deleteReview(int reviewNo);
}
