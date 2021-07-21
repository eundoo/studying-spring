package com.sample.web.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//form.jsp에 name에 똑같은 이름으로 만들어야함
public class UserRegisterForm {

	private String id;
	private String password;
	private String passwordConfirm;
	private String name;
	private String email;
	private String phone;
}
