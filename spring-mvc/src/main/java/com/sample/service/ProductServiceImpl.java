package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.ProductDao;
import com.sample.dao.ReviewDao;
import com.sample.vo.Product;
import com.sample.vo.Review;

/**
 * 상품정보관련 업무로직 메소드를 전부 구현하고 있는 구현 클래스다.<br />
 * @author lee_e
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired ProductDao productDao;
	@Autowired ReviewDao reviewDao;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	@Override
	public Product getProductDetail(int productNo) {
		Product product = productDao.getProductByNo(productNo);
		if (product != null) {
			product.setReviews(reviewDao.getReviewsByProductNo(productNo));
		}
		return product;
	}
	
	@Override
	public void addReview(Review review) {
		reviewDao.insertReview(review);
		
		Product product = productDao.getProductByNo(review.getProductNo());
		product.setReviewCnt(product.getReviewCnt() + 1);
		productDao.updateProduct(product);
	}
	
	@Override
	public void removeReview(int reviewNo, String userId) {
		Review review = reviewDao.getReviewByNo(reviewNo);
		reviewDao.deleteReview(review.getNo());
		
		Product product = productDao.getProductByNo(review.getProductNo());
		product.setReviewCnt(product.getReviewCnt() - 1);
		productDao.updateProduct(product);		
	}

}
