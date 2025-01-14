package com.solo.poker.filter;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solo.poker.jwt.JWTGenerator;
import com.solo.poker.user.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

        private final AuthenticationManager authenticationManager;
        private final JWTGenerator jwtGenerator;

        public LoginFilter(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
                this.authenticationManager = authenticationManager;
                this.jwtGenerator = jwtGenerator;
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                        throws AuthenticationException {

                // 클라이언트 요청에서 username, password 추출
                String username = null;
                String password = null;

                try {
                        BufferedReader reader = request.getReader();
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                                sb.append(line);
                        }
                        String body = sb.toString();

                        ObjectMapper mapper = new ObjectMapper();
                        LoginRequest loginRequest = mapper.readValue(body, LoginRequest.class);

                        username = loginRequest.getUsername();
                        password = loginRequest.getPassword();

                } catch (IOException e) {
                        e.printStackTrace();
                }

                // 스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                                password, null);

                // token에 담은 검증을 위한 AuthenticationManager로 전달
                return authenticationManager.authenticate(authToken);
        }

        // 로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain, Authentication authentication) {

                // UserDetailsS
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

                String username = customUserDetails.getUsername();

                // 60 * 60 * 10L = 10시간
                String token = jwtGenerator.createJwt(username, 60 * 60 * 10L);

                response.addHeader("Authorization", "Bearer " + token);
        }

        // 로그인 실패시 실행하는 메소드
        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException failed) {

                // 로그인 실패시 401 응답 코드 반환
                response.setStatus(401);
        }

        private static class LoginRequest {
                private String username;
                private String password;

                public String getUsername() {
                        return username;
                }

                public String getPassword() {
                        return password;
                }
        }
}
