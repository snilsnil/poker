package com.solo.poker;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.solo.poker.user.LogInUser;
import com.solo.poker.user.User;
import com.solo.poker.user.UserController;
import com.solo.poker.user.UserRepository;
import com.solo.poker.user.UserService;

@SpringBootTest
class PokerApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	// 회원가입시 받은 사용자 정보를 DB에 저장
	@Test
	public void usertest() {
		// User 객체 직접 생성
		User user = new User();
		user.setUserId("test");
		user.setPassword("1!qqqqqq");
		user.setUsername("test1");
		user.setEmail("test@teest.com");

		// UserController의 signup 호출
		userController.signup(user);
	}

	@Test
	public void testOptionalUser() {

		LogInUser logInUser = new LogInUser();

		logInUser.setUserId("test");
		logInUser.setPassword("1!qqqqqq");

		Optional<User> optionalUser = userRepository.findByUserId(logInUser.getUserId());

		System.out.println(optionalUser);
	}

	@Test
	public void testLogin() {

		LogInUser logInUser = new LogInUser();

		logInUser.setUserId("test");
		logInUser.setPassword("1!qqqqqq");

		String result = userController.login(logInUser);
		System.out.println(result);
	}
}
