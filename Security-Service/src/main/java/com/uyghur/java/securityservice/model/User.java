package com.uyghur.java.securityservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Username is mandatory")
    private String username;

    @Email
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Email is mandatory")
    private String password;

}
