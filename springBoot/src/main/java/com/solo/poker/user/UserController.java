package com.solo.poker.user;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        // 회원가입 정보를 받아서 DB에 저장하기
        String result = userService.createUser(user);

        return result;
    }

    @PostMapping("/signin")
    public String login(@RequestBody LogInUser logInUser) {
        // ID와 비밀번호를 받아서 사용자가 있는지 확인하기
        String result = userService.login(logInUser);

        return result;
    }

}
