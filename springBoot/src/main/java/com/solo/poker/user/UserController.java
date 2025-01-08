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
        // user 객체에는 클라이언트에서 보낸 데이터가 자동으로 매핑됩니다.
        String result = userService.createUser(user);

        if ("success".equals(result)) {
            return "회원가입 성공";
        } else {
            return "회원가입 실패: " + result;
        }
    }
}

