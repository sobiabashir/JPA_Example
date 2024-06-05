package com.test.jpa_example.Repository;

import com.test.jpa_example.model.AppUser;
import com.test.jpa_example.model.Book;
import com.test.jpa_example.model.BookLoan;
import com.test.jpa_example.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    detailsRepository detailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookLoanRepository bookLoanRepository;

    @Override
    public void run(String... args) throws Exception {
        LocalDate date = LocalDate.now();
        Details details = new Details("Jane Doe", "jane.doe@example.com",date);
        Details details1 = new Details("Jfhhgf", "john.doe@example.com",date);

        detailsRepository.save(details);
        detailsRepository.save(details1);
        AppUser appUser1 = new AppUser("sobia", "123456");
        AppUser appUser2 = new AppUser("sadia", "123456");


        appUserRepository.save(appUser1);
        appUserRepository.save(appUser2);

        Book book1 = new Book("1234567890", "Java101",20);
        Book book2 = new Book("1234567891", "Java102", 20);
        Book book3 = new Book("1234567892", "Java103", 20);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);


    }
}