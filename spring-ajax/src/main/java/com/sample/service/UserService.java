package com.sample.service;

import com.sample.vo.User;

public interface UserService {

	User login(String id, String password);
}
