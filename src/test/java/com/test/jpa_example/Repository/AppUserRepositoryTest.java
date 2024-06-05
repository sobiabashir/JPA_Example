
package com.test.jpa_example.Repository;


import com.test.jpa_example.model.AppUser;
import com.test.jpa_example.model.Details;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AppUserRepositoryTest {
@Autowired
    AppUserRepository appUserRepository;

@Autowired
    detailsRepository detailsRepository;


@Test
//@Transactional
public void testSave() {
    LocalDate date = LocalDate.now();
    Details details = new Details("Jane Doe", "jane.doe@example.com",  date );
    detailsRepository.save(details);

    AppUser appUser = new AppUser("sobia", "123456");

    appUser.setRegDate( LocalDate .now());
    appUser.setUserDetails(details);

    AppUser savedAppUser = appUserRepository.save(appUser);
    Assertions.assertNotNull(savedAppUser);
   // Assertions.assertNotNull(savedAppUser.getId());
}

    @Test
    public void testFindById() {
        LocalDate date = LocalDate.now();
        Details details = new Details("Jane Doe", "jane.doe@example.com",date);
        detailsRepository.save(details);

        AppUser appUser = new AppUser("sobia", "123456");
        appUser.setRegDate(LocalDate.now());
        appUser.setUserDetails(details);

        AppUser savedAppUser = appUserRepository.save(appUser);
        int appUserId = savedAppUser.getId();
        Assertions.assertNotNull(appUserId);

       
        Optional<AppUser> retrievedAppUserOptional = appUserRepository.findById(appUserId);
        Assertions.assertTrue(retrievedAppUserOptional.isPresent());

        AppUser retrievedAppUser = retrievedAppUserOptional.get();
        Assertions.assertEquals(savedAppUser.getId(), retrievedAppUser.getId());
        Assertions.assertEquals(savedAppUser.getUsername(), retrievedAppUser.getUsername());
        Assertions.assertEquals(savedAppUser.getPassword(), retrievedAppUser.getPassword());
        Assertions.assertEquals(savedAppUser.getRegDate(), retrievedAppUser.getRegDate());
        Assertions.assertEquals(savedAppUser.getUserDetails().getName(), retrievedAppUser.getUserDetails().getName());
        Assertions.assertEquals(savedAppUser.getUserDetails().getEmail(), retrievedAppUser.getUserDetails().getEmail());

    }

    @Test
    public void testFindDetailsByNameContainingIgnoreCase() {
        // Save some details entities
       LocalDate date = LocalDate.now();
        Details details1 = new Details("Jane Doe", "jane.doe@example.com",date);
        Details details2 = new Details("John Smith", "john.smith@example.com",date);
        Details details3 = new Details("janet Roe", "janet.roe@example.com",date);
        detailsRepository.save(details1);
        detailsRepository.save(details2);
        detailsRepository.save(details3);

        // Test findByNameContainingIgnoreCase
        List<Details> foundDetails = detailsRepository.findByNameContainingIgnoreCase("jane");
        Assertions.assertEquals(2, foundDetails.size());
        Assertions.assertTrue(foundDetails.stream().anyMatch(details -> details.getName().equals("Jane Doe")));
        Assertions.assertTrue(foundDetails.stream().anyMatch(details -> details.getName().equals("janet Roe")));

        foundDetails = detailsRepository.findByNameContainingIgnoreCase("SMITH");
        Assertions.assertEquals(1, foundDetails.size());
        Assertions.assertEquals("John Smith", foundDetails.get(0).getName());
    }


}
