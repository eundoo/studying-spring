package com.sample.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class User {

	String id;
	String name;
	@JsonProperty(access = Access.WRITE_ONLY)
	String password;
	String email;
	String phone;
	String status;
	int point;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	Date createdDate;
}
