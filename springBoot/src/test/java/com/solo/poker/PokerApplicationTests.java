package com.solo.poker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.solo.poker.user.LogInUser;
import com.solo.poker.user.User;
import com.solo.poker.user.UserController;
import com.solo.poker.user.UserRepository;

@SpringBootTest
class PokerApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private UserRepository userRepository;

	// 회원가입시 받은 사용자 정보를 DB에 저장
	@Test
	public void usertest() {
		// User 객체 직접 생성
		User user = new User();
		user.setUsername("test");
		user.setPassword("1!qqqqqq");
		user.setNickname("test1");
		user.setEmail("test@teest.com");

		// UserController의 signup 호출
		userController.signup(user);
	}

	@Test
	public void testOptionalUser() {

		LogInUser logInUser = new LogInUser();

		logInUser.setUsername("test");
		logInUser.setPassword("1!qqqqqq");

		User optionalUser = userRepository.findByUsername(logInUser.getUsername());

		System.out.println(optionalUser);
	}
}
