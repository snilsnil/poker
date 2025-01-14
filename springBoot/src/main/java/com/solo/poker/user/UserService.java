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
            boolean checkusername = userRepository.existsByUsername(user.getUsername());
            boolean checkEmail = userRepository.existsByEmail(user.getEmail());

            System.out.println("checkusername: " + checkusername);

            if (checkusername && checkEmail) {
                throw new UserAlreadyExistsException("ID와 이메일이 존재합니다.");
            } else if (checkusername) {
                throw new UserAlreadyExistsException("ID가 존재합니다.");
            } else if (checkEmail) {
                throw new UserAlreadyExistsException("이메일이 존재합니다.");
            }

            // 비밀번호 암호화 및 사용자 저장
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return "success";

        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return e.getMessage();

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return "DB에서 오류가 생겼습니다. 잠시후 다시 시도하세요.";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "예상치 못한 오류가 생겼습니다. 잠시후 다시 시도하세요.";
        }
    }
}
