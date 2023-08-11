package com.apress.book.ldap.client;

import com.apress.book.ldap.domain.Patron;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:repositoryContext-testcontainers.xml")
public class LdapOperationsClientContainerTest {

    @Container
    public static DockerComposeContainer ldap = new DockerComposeContainer(
            new File("src/test/resources/docker-compose.yml"))
                    .waitingFor("opendj_1", Wait.forLogMessage(".*started*\\n", 1)).withLocalCompose(true);

    @Autowired
    LdapOperationsClient client;

    @Test
    public void should_remove_patron() {

        client.remove("patron100");

        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(100, patrons.size()));
    }

    @Test
    public void should_return_patrons() {
        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(100, patrons.size()));
    }

    @Test
    public void should_add_patron() {
        Patron patron = new Patron(null, "Andres", "Sacco", "Andres Sacco", "sacco.andres@inflix.com");
        client.add("patron102", patron);

        List<Patron> patrons = client.search();

        assertAll(() -> assertNotNull(patrons), () -> assertEquals(101, patrons.size()));
    }

}
