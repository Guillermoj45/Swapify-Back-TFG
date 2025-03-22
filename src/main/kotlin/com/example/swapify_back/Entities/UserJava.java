package com.example.swapify_back.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "Users")
public class UserJava {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id = null;

    @Column(name = "email", nullable = false, unique = true)
    private String email = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = null;
}
