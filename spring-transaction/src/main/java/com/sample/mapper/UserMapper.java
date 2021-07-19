package com.sample.mapper;

import java.util.List;

import com.sample.vo.User;

public interface UserMapper {

	void insertUser(User user);
	
	void deleteUser(String userId);
	
	void updateUser(User user);
	
	User getUserById(String userId);
	
	User getUserByEmail(String userEmail);
	
	List<User> getUserByPhone(String userPhoneNumber);
}
