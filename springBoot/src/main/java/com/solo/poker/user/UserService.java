package com.solo.poker.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.dao.DataAccessException;

import com.solo.poker.jwt.JWTGenerator;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    public String createUser(User user) {
        try {
            boolean checkUserId = userRepository.existsByUserId(user.getUserId());
            boolean checkEmail = userRepository.existsByEmail(user.getEmail());

            if (checkUserId && checkEmail) {
                throw new UserAlreadyExistsException("ID와 이메일이 존재합니다.");
            } else if (checkUserId) {
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

    public String login(LogInUser logInUser) {
        try {
            Optional<User> optionalUser = userRepository.findByUserId(logInUser.getUserId());

            if (optionalUser.isEmpty()) {
                return "회원이 존재하지 않습니다. 회원가입을 먼저 해주세요.";
            }

            User user = optionalUser.get();

            if (!passwordEncoder.matches(logInUser.getPassword(), user.getPassword())) {
                return "비밀번호가 틀립니다.";
            }

            String jwtToken = jwtGenerator.generateToken(user);

            return jwtToken;

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return "DB에서 오류가 생겼습니다. 잠시후 다시 시도하세요.";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "예상치 못한 오류가 생겼습니다. 잠시후 다시 시도하세요.";
        }
    }
}
