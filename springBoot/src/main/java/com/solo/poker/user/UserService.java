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
            boolean checkUserId = userRepository.existsByUserId(user.getUserId());
            boolean checkEmail = userRepository.existsByEmail(user.getEmail());
            if (checkEmail == true
                    && checkUserId == true)
                throw new UserAlreadyExistsException("ID와 이메일이 존재합니다.");
            else if (checkUserId == true)
                throw new UserAlreadyExistsException("ID가 존재합니다.");
            else if (checkEmail == true)
                throw new UserAlreadyExistsException("이메일이 존재합니다.");

            // 비밀번호 암호화 및 사용자 저장
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);

            return "success";

        } catch (UserAlreadyExistsException e) {
            // 사용자 이미 존재하는 경우 예외 던짐
            System.out.println(e.getMessage());
            return e.getMessage();

        } catch (IllegalArgumentException e) {
            // 입력 데이터가 잘못된 경우
            System.out.println(e.getMessage());
            return "입력하신 데이터가 잘못되었습니다.";

        } catch (DataAccessException e) {
            // 데이터베이스 관련 오류
            System.out.println(e.getMessage());
            return "DB에서 오류가 생겼습니다. 잠시후 다시 시도하세요.";

        } catch (Exception e) {
            // 기타 예상치 못한 오류
            System.out.println(e.getMessage());
            return "예상치 못한 오류가 생겼습니다. 잠시후 다시 시도하세요.";
        }
    }
}
