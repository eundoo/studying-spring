package com.sample.di.demo5;

import org.springframework.stereotype.Repository;

//이름을 굳이 정하지 않아도 되는데 지정 할 수는 있다.
@Repository("demo5.UserPointHistoryDao")
public class UserPointHistoryDao {

	public void getPointHistory(String userId) {
		System.out.println("["+userId+"] 사용자의 포인트 변경이력을 조회합니다.");

	}
}
