package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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

    @Column (nullable = false, length = 20)
    @Setter private String isbn;


    @Column (nullable = false)
    @Setter private String title;

    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @Setter private Set<Author> authors;

    @Column
    @Setter private int maxLoanDays;

    @OneToMany(mappedBy = "book")
    @Setter private List<BookLoan> loans;

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }
    public Book addBook(String isbn, String title, int maxLoanDays, Author author) {
        Book newBook = new Book(isbn,title,maxLoanDays);
        newBook.authors.add(author);
        return newBook;
    }
}