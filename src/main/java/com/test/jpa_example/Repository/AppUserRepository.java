package com.test.jpa_example.Repository;

import com.test.jpa_example.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {

    //SELECT * from appUser where username = ?
    AppUser findAppUserByUsername(String username);

    //SELECT * from appUser where RegDate BETWEEN ? and ?
    List<AppUser> findAppUserByRegDateBetween(LocalDate date1, LocalDate date2);

    AppUser findAppUserByUserDetails_Id(int id);

    AppUser findAppUserByUserDetails_EmailIgnoreCase(String email);


}