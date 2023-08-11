package com.apress.book.ldap.test;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class HelloWorldContainerTest {

    @Container
    public static GenericContainer container = new GenericContainer(DockerImageName.parse("testcontainers/helloworld"));

    @Test
    public void should_works_the_container() {

    }
}
