package com.sample.service;

import com.sample.vo.User;

public interface UserService {
	/**
	 * 지정된 사용자 정보를 회원가입시킨다.
	 * @param user 사용자정보
	 */
	void  registerUser(User user);
	
	void login(String userId, String password);
}
