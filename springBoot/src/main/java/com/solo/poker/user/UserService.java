package com.solo.poker.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String createUser(User user) {
        try {
            // 비밀번호 암호화 및 사용자 저장
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);

            return "User created successfully";

        } catch (IllegalArgumentException e) {
            // 입력 데이터가 잘못된 경우
            return "Error: " + e.getMessage();

        } catch (DataAccessException e) {
            // 데이터베이스 관련 오류
            return "Database error: " + e.getMessage();

        } catch (Exception e) {
            // 기타 예상치 못한 오류
            return "Unexpected error: " + e.getMessage();
        }
    }
}
