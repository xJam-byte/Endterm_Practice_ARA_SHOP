package com.example.endterm_project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id // pizda
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String phone_number;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

}