
package com.test.jpa_example.Repository;


import com.test.jpa_example.model.AppUser;
import com.test.jpa_example.model.Details;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AppUserrepositoryTest {
@Autowired
    AppUserRepository appUserRepository;

@Autowired
    detailsRepository detailsRepository;


@Test
//@Transactional
public void testSave() {
    Details details = new Details("Jane Doe", "jane.doe@example.com");
    detailsRepository.save(details);

    AppUser appUser = new AppUser("sobia", "123456");

    appUser.setRegistrationDate(LocalDateTime.now());
    appUser.setDetails(details);

    AppUser savedAppUser = appUserRepository.save(appUser);
    Assertions.assertNotNull(savedAppUser);
    Assertions.assertNotNull(savedAppUser.getAppUserId());
}

    @Test
    public void testFindById() {
        // First, save an AppUser entity
        Details details = new Details("Jane Doe", "jane.doe@example.com");
        detailsRepository.save(details);

        AppUser appUser = new AppUser("sobia", "123456");
        appUser.setRegistrationDate(LocalDateTime.now());
        appUser.setDetails(details);

        AppUser savedAppUser = appUserRepository.save(appUser);
        int appUserId = savedAppUser.getAppUserId();
        Assertions.assertNotNull(appUserId);

        // Retrieve the AppUser entity by its ID
        Optional<AppUser> retrievedAppUserOptional = appUserRepository.findById(appUserId);
        Assertions.assertTrue(retrievedAppUserOptional.isPresent());

        AppUser retrievedAppUser = retrievedAppUserOptional.get();
        Assertions.assertEquals(savedAppUser.getAppUserId(), retrievedAppUser.getAppUserId());
        Assertions.assertEquals(savedAppUser.getUserName(), retrievedAppUser.getUserName());
        Assertions.assertEquals(savedAppUser.get_password(), retrievedAppUser.get_password());
        Assertions.assertEquals(savedAppUser.getRegistrationDate(), retrievedAppUser.getRegistrationDate());
        Assertions.assertEquals(savedAppUser.getDetails().getName(), retrievedAppUser.getDetails().getName());
        Assertions.assertEquals(savedAppUser.getDetails().getEmail(), retrievedAppUser.getDetails().getEmail());

    }

    @Test
    public void testFindDetailsByNameContainingIgnoreCase() {
        // Save some details entities
        Details details1 = new Details("Jane Doe", "jane.doe@example.com");
        Details details2 = new Details("John Smith", "john.smith@example.com");
        Details details3 = new Details("janet Roe", "janet.roe@example.com");
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
