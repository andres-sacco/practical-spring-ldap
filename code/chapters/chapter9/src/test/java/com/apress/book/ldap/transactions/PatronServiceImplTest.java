package com.apress.book.ldap.transactions;

import com.apress.book.ldap.domain.Patron;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-test.xml")
class PatronServiceImplTest {

    @Autowired
    private PatronService patronService;

    // Uncomment @Test when PatronServiceImpl's delete is not simulating rollback
    // @Test
    void should_delete_a_patron() {

        assertThrows(RuntimeException.class, () -> {
            patronService.delete("patron10001");
        });
    }

    @Test
    void should_not_create_a_patron() {
        Patron patron = new Patron();

        patron.setUid("patron10001");
        patron.setLastName("Patron10001");
        patron.setFullName("Test Patron10001");

        assertThrows(RuntimeException.class, () -> {
            patronService.create(patron);
        });

        assertThrows(EmptyResultDataAccessException.class, () -> {
            patronService.find("patron10001");
        });
    }
}