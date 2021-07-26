package com.sample.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private int no;
	private String name;
	private String maker;
	private String category;
	private int price;
	private int discountPrice;
	private int stock;
	private String soldOut;
	private int reviewCnt;
	private Date createdDate;
	private List<Review> reviews;
	
	
}
