package com.sample.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.exception.MallBusinessException;
import com.sample.mapper.ProductMapper;
import com.sample.vo.Product;

/*
 * @Service
 * 		-<context:component-scan />설정으로 클래스가 자동으로 스캔되고, 객체  생성 후 스프링의 빈으로 등록된다.
 */
@Service
@Transactional
public class ProductService {

	/**
	 * @Autowired
	 * 	- 비즈니스 로직 수행에 필요한 db access작업을 실행할 매퍼 인터페이스의 구현객체가 자동으로 의존성 주입된다.
	 */
	@Autowired
	private ProductMapper productMapper;

	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
	public List<Product> getAllProductList() {
		return productMapper.getAllProducts();
	}
	
	public void insertNewProduct(Product product) {
		productMapper.insertProduct(product);
	}
	
	public void addProduct(int productNo, int amount) {
		Product product = productMapper.getProductByNo(productNo);
		product.setStock(product.getStock() + amount);
		
		productMapper.updateProduct(product);
	}
	
	public Product getProductDetail(int productNo) {
		Product product = productMapper.getProductByNo(productNo);
		if(product == null) {
			throw new MallBusinessException("상품번호가 올바르지 않습니다.");
		}
		
		return product;
	}
}
