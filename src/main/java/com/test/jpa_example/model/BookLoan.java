package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "appUser_id")
    @Setter private AppUser borrower;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id")
    @Setter private Book book;

    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;

    }
}