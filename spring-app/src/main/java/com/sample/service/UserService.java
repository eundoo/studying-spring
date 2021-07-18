package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sample.exception.MallBusinessException;
import com.sample.mapper.UserMapper;
import com.sample.vo.User;

@Service
public class UserService {

	@Value("${user.default.deposit.point}")
	private int defaultDepositPoint;
	@Autowired
	private UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	/**
	 * 사용자 정보를 전달 받앙서 회원가입 처리한다.
	 * @param user 신규 사용자 정보
	 */
	public void addNewUser(User user) {
		User savedUser = userMapper.getUserById(user.getId());
		if(savedUser != null) {
			throw new MallBusinessException("["+user.getId()+"]는 사용중인 아이디 입니다.");
		}
		savedUser = userMapper.getUserByEmail(user.getId());
		if(savedUser != null) {
			throw new MallBusinessException("["+user.getEmail()+"]은 이미 등록된 이메일 입니다.");
		}
		List<User> savedUsers = userMapper.getUserByPhone(user.getPhone());
		if(!savedUsers.isEmpty()) {
			throw new MallBusinessException("["+user.getPhone()+"]는 사용중인 아이디 입니다.");
		}
		
		//비밀번호 암호화
		String encodedPassword = DigestUtils.sha256Hex(user.getPassword());
		user.setPassword(encodedPassword);
		
		//신규 사용자 정보 저장
		userMapper.insertUser(user);
		
		//신규 가입한 사용자에게 기본 적립포인트 지급
		user = userMapper.getUserById(user.getId());
		user.setPoint(defaultDepositPoint);
		userMapper.updateUser(user);
	}
	
	/**
	 * 아이디를 전달받아서 사용자 정보 삭제한다.
	 * @param userId 사용자 아이디
	 */
	public void removeUser(String userId) {
		userMapper.deleteUser(userId);
	}
	
	/**
	 * 아이디를 전달받아서 조회된 사용자 정보를 반환한다.
	 * @param userId 아이디
	 * @return	사용자 정보
	 */
	
	//사실 얘는 단위테스트 할 필요가 없는게 SQL만 잘 적었다면 오류가 안나기 때문에 
	//업무 로직이 잘 돌아가냐 안돌아가냐 그런걸 테스트 하는거지 쿼리에 관해서 단위 테스트를 하는게 아니기 때문에
	public User getUserDetail(String userId) {
		return userMapper.getUserById(userId);
	}
}
