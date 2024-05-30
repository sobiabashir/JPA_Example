package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(nullable = false, length = 100, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String _password;

    @Column
    private LocalDateTime registrationDate;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private Details details;

    public void setPassword(String password) {
        this._password = password;
    }


    public AppUser(String userName, String password) {
        this.userName = userName;
        this._password = password;
    }
}
