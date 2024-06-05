package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Setter private LocalDate loanDate;

    @Column
    @Setter private LocalDate dueDate;

    @Column
    @Setter private boolean returned;

    @ManyToOne
    @JoinColumn(name = "AppUser_id")
    private AppUser borrower;

    @ManyToOne
    @JoinColumn(name = "Book_id")
    private Book book;
    @ManyToOne(optional = false)
    private AppUser appUsers;

    public AppUser getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(AppUser appUsers) {
        this.appUsers = appUsers;
    }
}