package com.sample.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItem {

	private int no;
	private String userId;
	private int productNo;
	private int amount;
	private Date createdDate;
	
}
