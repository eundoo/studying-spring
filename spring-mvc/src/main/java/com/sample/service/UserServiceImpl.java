package com.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.CartItemDao;
import com.sample.dao.ReviewDao;
import com.sample.dao.UserDao;
import com.sample.dto.CartItemDto;
import com.sample.exception.LoginException;
import com.sample.exception.UserRegisterException;
import com.sample.vo.Review;
import com.sample.vo.User;
import com.sample.web.utils.SessionUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired CartItemDao cartItemDao;
	@Autowired ReviewDao reviewDao;
	@Autowired UserDao userDao;	
	
	@Override
	public void registerUser(User user) {
		User savedUser = userDao.getUserById(user.getId());
		if (savedUser != null) {
			throw new UserRegisterException("아이디 중복", "["+user.getId()+"]는 이미 사용중인 아이디입니다.");
		}
		savedUser = userDao.getUserByEmail(user.getEmail());
		if (savedUser != null) {
			throw new UserRegisterException("이메일 중복", "["+user.getEmail()+"]은 이미 사용중인 이메일입니다.");
		}
		
		String secretPassword = DigestUtils.sha256Hex(user.getPassword());
		user.setPassword(secretPassword);
		
		userDao.insertUser(user);
	}
	
	@Override
	public void login(String userId, String userPassword) {
		User user = userDao.getUserById(userId);
		if (user == null) {
			throw new LoginException("아이디/비밀번호 오류", "아이디 혹은 비밀번호가 유효하지 않습니다.");
		}
		if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
			throw new LoginException("사용중지된 회원", "탈퇴 혹은 일시정지 처리된 사용자입니다.");
		}
		
		String secretPassword = DigestUtils.sha256Hex(userPassword);
		if (!user.getPassword().equals(secretPassword)) {
			throw new LoginException("아이디/비밀번호 오류", "아이디 혹은 비밀번호가 유효하지 않습니다.");
		}
		
		SessionUtils.addAttribute("LOGINED_USER", user);
	}
	
	@Override
	public Map<String, Object> getUserDetail(String userId) {
		Map<String, Object> userDetailMap = new HashMap<>();
		
		List<CartItemDto> cartItems = cartItemDao.getCartItemsByUserId(userId);
		List<Review> reviews = reviewDao.getReviewsByUserId(userId);
		User user = userDao.getUserById(userId);
		
		userDetailMap.put("cartItems", cartItems);
		userDetailMap.put("reviews", reviews);
		userDetailMap.put("user", user);
		
		return userDetailMap;
	}
}
