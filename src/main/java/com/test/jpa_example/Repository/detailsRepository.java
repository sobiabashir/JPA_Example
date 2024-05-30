package com.test.jpa_example.Repository;

import com.test.jpa_example.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface detailsRepository extends JpaRepository<Details, Integer> {
    // Basic CRUD operations are inherited from JpaRepository

    Optional<Details> findByEmail(String email);

    List<Details> findByNameContains(String name);

    List<Details> findByNameContainingIgnoreCase(String name);
}
