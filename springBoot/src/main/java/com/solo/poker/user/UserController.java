package com.solo.poker.user;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.solo.poker.jwt.JWTGenerator;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class UserController {

    private final UserService userService;

    private final JWTGenerator jwtGenerator;

    @GetMapping("/checkToken")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");

        if (jwtGenerator.isExpired(token)) {
            return ResponseEntity.status(401).body("Token expired");
        }

        return ResponseEntity.ok().body("Token valid");
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        System.out.println(user);
        // 회원가입 정보를 받아서 DB에 저장하기
        String result = userService.createUser(user);

        return result;
    }

}
