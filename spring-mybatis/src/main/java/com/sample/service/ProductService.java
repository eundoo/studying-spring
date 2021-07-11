package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mapper.ProductMapper;
import com.sample.vo.Product;

@Service
public class ProductService {
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
	
	public void addProducts(int productNo, int amount) {
		Product product = productMapper.getProductByNo(productNo);
		product.setStock(product.getStock() + amount);
		
		productMapper.updateProduct(product);
	}
}
