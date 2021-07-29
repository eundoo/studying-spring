package com.sample.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.ProductService;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired ProductService productService;
	
	
	
}
