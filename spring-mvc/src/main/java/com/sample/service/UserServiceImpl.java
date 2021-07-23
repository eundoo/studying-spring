package com.sample.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.CartItemDao;
import com.sample.dao.UserDao;
import com.sample.exception.SampleException;
import com.sample.vo.CartItem;
import com.sample.vo.User;
import com.sample.web.utils.SessionUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	//@Autowired
	//OrderService orderService;
	
	//@Autowired
	//PointHistoryDao pointHistoryDao;
	
	@Override
	public  void registerUser(User user) {
		User savedUser = userDao.getUserById(user.getId());
		if(savedUser != null) {
			throw new SampleException("ER_USER_001", "아이디 중복", "["+user.getId()+"]는 이미 사용중인 아이디 입니다.");
		}
		savedUser = userDao.getUserByEmail(user.getEmail());
		if(savedUser != null) {
			throw new SampleException("ERR_USER_001", "이메일 중복", "["+user.getEmail()+"]은 이미 사용중인 이메일 입니다.");
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
			throw new SampleException("ERR_USER_002", "아이디/비밀번호 오류", "아이디 혹은 비밀번호가 유효하지 않습니다.");
		}
		if(!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
			throw new SampleException("ERR_USER_002", "사용중지된 회원", "탈퇴 혹은 일시정지 처리된 사용자입니다.");			
		}
		String secretPassword = DigestUtils.sha256Hex(password);
		if(!user.getPassword().contentEquals(secretPassword)) {
			throw new SampleException("ERR_USER_002", "아이디/비밀번호 오류", "아이디 혹은 비밀번호가 유효하지 않습니다.");
		}
		
		//HttpSession객체에 사용자 인증이 완료된 사용자 정보를 
		//여기가 이제 유저정보를 세션객체에 담은거임
		SessionUtils.addAttribute("LOGINED_USER", user);
		
		//조회된 사용자 정보
	}
	
	@Override
	public Map<String, Object> getUserDetail(String id) {
		Map<String, Object> userDetailMap = new HashMap<>();
		
		//최신의 사용자 정보 조회
		User user = userDao.getUserById(id);
		
		userDetailMap.put("user", user);
		//userDetailMap.put("user", User객체);
		//userDetailMap.put("cartItems", 장바구니에 저장된 아이템정보들);
		//userDetailMap.put("orders", 최근 구매내약);
		//userDetailMap.put("reviews", 내가 작성한 리뷰);
		//userDetailMap.put("pointHistory", 포인트변경 이력);
	
		return userDetailMap;
	}
}
