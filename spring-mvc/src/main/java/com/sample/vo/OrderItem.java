package com.sample.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {

	private int orderNo;
	private int productNo;
	private int orderAmount;
	private int orderPrice;
	
}
