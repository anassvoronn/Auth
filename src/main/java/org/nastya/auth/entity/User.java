package org.nastya.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.nastya.auth.entity.enums.Role;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
