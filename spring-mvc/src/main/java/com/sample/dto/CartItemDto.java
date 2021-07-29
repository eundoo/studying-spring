package com.sample.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemDto {

	private int no;
	private int amount;
	private int productNo;
	private String name;
	private int price;
	private int discountPrice;
	private int orderPrice;
}
