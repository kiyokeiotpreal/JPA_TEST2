package com.example.jpa_test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JpaTest2Application {

    public static void main(String[] args) {
        SpringApplication.run(JpaTest2Application.class, args);
    }

}
