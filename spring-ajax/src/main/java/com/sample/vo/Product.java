package com.sample.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Product {

	long no;
	String name;
	String maker;
	String category;
	int price;
	int discountPrice;
	int stock;
	String soldOut;
	int reviewCnt;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	Date createdDate;
}
