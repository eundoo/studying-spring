package com.sample.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Review {

	int no;
	String title;
	String content;
	int productNo;
	User user;
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date createdDate;
}
