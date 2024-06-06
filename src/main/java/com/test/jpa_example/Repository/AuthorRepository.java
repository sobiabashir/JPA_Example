
package com.test.jpa_example.Repository;
import com.test.jpa_example.model.Author;
import com.test.jpa_example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    List<Author> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    List<Author> findByWrittenBooks_Id(int bookId);

    void updateAuthorNameById(int id, String firstName, String lastName);

    void deleteById(int id);
}
