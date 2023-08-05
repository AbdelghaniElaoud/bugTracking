package com.example.bugreporting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String password;

    // Constructors, getters, and setters (omitted for brevity)

    // Default constructor
    public User() {}

    // Parameterized constructor

    public User(String firstName, String lastName, String username, String email, UserRole role, UserStatus status, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.password = password;
    }


}