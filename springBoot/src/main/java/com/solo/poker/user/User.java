package com.solo.poker.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @Column(unique = true)
    private String id;

    private String password;
    private String username;

    @Column(unique = true)
    private String email;
}
