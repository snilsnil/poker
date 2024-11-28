package com.solo.poker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.solo.poker.user.User;
import com.solo.poker.user.UserController;

@SpringBootTest
class PokerApplicationTests {

	@Autowired
	private UserController userController;

	// 회원가입시 받은 사용자 정보를 DB에 저장
	@Test
	public void usertest() {
		// User 객체 직접 생성
		User user = new User();
		user.setId("snilsnil");
		user.setPassword("jy1234hy");
		user.setUsername("snilsnil");
		user.setEmail("heo6601@naver.com");

		// UserController의 signup 호출
		userController.signup(user);
	}
}
