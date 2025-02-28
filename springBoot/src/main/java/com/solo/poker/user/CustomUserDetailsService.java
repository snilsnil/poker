package com.solo.poker.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB에서 조회
        User user = userRepository.findByUsername(username);

        if (user != null) {

            // UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(user);
        }

        return null;
    }
}
