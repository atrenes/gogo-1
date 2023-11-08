package com.example.gogo.config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
public class CustomPostgreSQLContainer extends PostgreSQLContainer<CustomPostgreSQLContainer> {

    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:15-alpine");

    @BeforeAll
    public static void setUp() {
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void setDown() {
        postgreSQLContainer.stop();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }
}
