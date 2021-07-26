package com.sample.service;

import java.util.Map;

import com.sample.vo.User;

public interface UserService {

	/**
	 * 지정된 사용자정보로 회원가입을 수행하는 서비스
	 * @param user 사용자정보
	 */
	void registerUser(User user);
	
	/**
	 * 지정된 아이디와 비밀번호로 사용자인증을 수행하는 서비스
	 * @param id 사용자 아이디
	 * @param password 사용자 비밀번호
	 */
	void login(String userId, String userPassword);
	
	/**
	 * 지정된 아이디로 사용자의 상세정보를 제공하는 서비스
	 * @param id 사용자 아이디
	 * @return 사용자에 대한 상세정보가 포함된 Map객체
	 */
	Map<String, Object> getUserDetail(String userId);
}
