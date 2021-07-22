package com.sample.service;

import java.util.Map;

import com.sample.vo.User;

public interface UserService {
	/**
	 * 지정된 사용자 정보를 회원가입시킨다.
	 * @param user 사용자정보
	 */
	void  registerUser(User user);
	
	/**
	 * 지정된 아이디와 비밀번호로 사용자인증을 수행하는 서비스
	 * @param userId 사용자 아이디
	 * @param password 사용자 비밀번호
	 */
	void login(String userId, String password);
	
	/**
	 * 지정된 아이디로 사용자의 상세정보를 제공하는 서비스
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserDetail(String id);
}
