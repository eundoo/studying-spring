package com.sample.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Review {

	private int no;
	private String title;
	private String content;
	private int productNo;
	private String userId;
	private Date createdDate;
	
	public Review(String title, String content, int productNo, String userId) {
		this.title = title;
		this.content = content;
		this.productNo = productNo;
		this.userId = userId;
	}
}
