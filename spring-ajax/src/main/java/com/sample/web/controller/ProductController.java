package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.ProductService;
import com.sample.web.model.service.ResponseDataService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ResponseDataService responseDataService;
	
	@RequestMapping("catogories")
	public MultipleResponseData<String> categories() {
		List<String> categories = productService.getAllCategories();
		return responseDataSevice.getMultipleResponseData(categories);

	}
	
	@RequestMapping("/products")
	public MultipleResponseData<Product> products(@RequestParam("cat") String catagories)) {
		List<Product> products = productService.getProductsByCategory(category);
		return responseDataService.getMultipleResponseData(categores)
	}
