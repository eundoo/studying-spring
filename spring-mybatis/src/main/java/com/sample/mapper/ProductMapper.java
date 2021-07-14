package com.sample.mapper;

import java.util.List;

import com.sample.vo.Product;

public interface ProductMapper {

	void insertProduct(Product produdct);
	
	void updateProduct(Product product);
	
	List<Product> getAllProducts();

	Product getProductByNo(int productNo);
}
