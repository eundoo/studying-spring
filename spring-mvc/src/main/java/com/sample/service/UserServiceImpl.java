package com.sample.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.UserDao;
import com.sample.vo.User;
import com.sample.web.utils.SessionUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public  void registerUser(User user) {
		User savedUser = userDao.getUserById(user.getId());
		if(savedUser != null) {
			throw new RuntimeException("["+user.getId()+"]는 이미 사용중인 아이디 입니다.");
		}
		savedUser = userDao.getUserByEmail(user.getEmail());
		if(savedUser != null) {
			throw new RuntimeException("["+user.getEmail()+"]는 이미 등록된 이메일 입니다.");
		}
		
		String secretPassword = DigestUtils.sha256Hex(user.getPassword());
		user.setPassword(secretPassword);
		
		userDao.insertUser(user);
		
		//사용자 아이디로 사용자 정보가 조회되면 예외발생
		//사용자 이메일로 사용자 정보가 조회되면 예외발생
		
		//사용자 비밀번호를 암호화한다.
		//사용자 정보를 저장한다.
	}
	
	@Override
	public void login(String userId, String password) {
		//사용자 정보 조회 - null인지 체크, 삭제된 사용자인지 체크, 비밀번호가 일치하는지 체크
		User user = userDao.getUserById(userId);
		if(user == null) {
			throw new RuntimeException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		if(!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
			throw new RuntimeException("탈퇴처리된 사용자 입니다.");			
		}
		String secretPassword = DigestUtils.sha256Hex(password);
		if(!user.getPassword().contentEquals(secretPassword)) {
			throw new RuntimeException("아이디혹은 비밀번호가 일치하지 않습니다.");
		}
		
		//HttpSession객체에 사용자 인증이 완료된 사용자 정보를 
		//여기가 이제 유저정보를 세션객체에 담은거임
		SessionUtils.addAttribute("LOGINED_USER", user);
		
		//조회된 사용자 정보
	}
}
