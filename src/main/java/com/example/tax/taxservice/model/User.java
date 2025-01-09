package com.example.tax.taxservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PK_USER_ID")
    private Long pkUserId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="LOGIN")
    private String login;

    @Column(name="PASS_HASH_CODE")
    private String passHashCode;

    @Column(name="ACTIVE")
    private Boolean active;

    @Column(name = "IS_ADMIN")
    private Boolean isAdmin;

}
