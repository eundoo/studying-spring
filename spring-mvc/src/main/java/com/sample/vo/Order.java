package com.sample.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {

	private int no;
	private String userId;
	private String status;
	private int totalPrice;
	private int totalSavePoint;
	private Date createdDate;
}
