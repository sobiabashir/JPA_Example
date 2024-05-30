package com.test.jpa_example.Repository;

import com.test.jpa_example.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUserName(String userName);

    List<AppUser> findByRegistrationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<AppUser> findByDetailsDetailId(int detailId);

    Optional<AppUser> findByDetailsEmailIgnoreCase(String email);
}
