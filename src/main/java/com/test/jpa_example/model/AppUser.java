package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    @Setter private String username;

    @Column(nullable = false)
    @Setter private String password;

    @Column
    @Setter private LocalDate regDate;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Details userDetails;

    @OneToMany(mappedBy = "borrower")
    private List<BookLoan> bookLoans;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();

    }
    protected void addBookLoan(BookLoan loan) {
        bookLoans.add((loan));
        loan.setBorrower(this);
    }
}