package com.system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RestApiTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestApiTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
