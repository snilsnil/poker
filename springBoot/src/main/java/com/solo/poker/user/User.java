package com.solo.poker.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username; // username 개별 유니크 제약 조건

    private String password;
    private String nickname;

    @Column(name = "email", unique = true)
    private String email; // email 개별 유니크 제약 조건
}
