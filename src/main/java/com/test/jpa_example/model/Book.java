package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, length = 13)
    @Setter private String isbn;


    @Column (nullable = false)
    @Setter private String title;

    @Column
    @Setter private int maxLoanDays;

    @OneToMany(mappedBy = "book")
    @Setter private List<BookLoan> loans;

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }
}