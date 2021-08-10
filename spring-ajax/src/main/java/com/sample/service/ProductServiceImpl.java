package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.ProductDao;
import com.sample.dao.ReviewDao;
import com.sample.dao.UserDao;
import com.sample.vo.Product;
import com.sample.vo.Review;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	@Autowired
	ReviewDao reviewDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public List<String> getAllCategories() {
		return productDao.getCategories();
	}
	@Override
	public List<Product> getProductsByCategory(String category) {
		return productDao.getProducts(category);
	}
	@Override
	public Product getProductDetail(int productNo) {
		return productDao.getProduct(productNo);
	}
	@Override
	public List<Review> getReviewsByProductNo(int productNo) {
		return reviewDao.getReviews(productNo);
	}
	@Override
	public void addNewReview(Review review) {
		reviewDao.insertReview(review);
		Product product = productDao.getProduct(review.getProductNo());
		product.setReviewCnt(product.getReviewCnt() + 1);
		productDao.updateProduct(product);
	}
	@Override
	public void deleteReview(int reviewNo) {
		Review review = reviewDao.getReview(reviewNo);
		reviewDao.deleteReview(reviewNo);
		
		Product product = productDao.getProduct(review.getProductNo());
		product.setReviewCnt(product.getReviewCnt() - 1);
		productDao.updateProduct(product);
	}
	
	
}
