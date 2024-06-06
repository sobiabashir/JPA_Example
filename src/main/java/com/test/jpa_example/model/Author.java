package com.test.jpa_example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    @Column(nullable = false)
    @Setter
    private String firstName;


    @Column(nullable = false)
    @Setter
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> writtenBooks = new HashSet<>();


    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Author createAuthor(String firstName, String lastName) {
        return new Author(firstName,lastName);
    }


}
