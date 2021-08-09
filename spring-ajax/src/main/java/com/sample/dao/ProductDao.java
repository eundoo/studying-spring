package com.sample.dao;

import java.util.List;

import com.sample.vo.Product;

public interface ProductDao {

	List<String> getCategories();
	List<Product> getProducts(String category);
	Product getProduct(int no);
	void updateProduct(Product product);
}
