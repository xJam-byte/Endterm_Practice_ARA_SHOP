package com.example.endterm_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication(exclude = {ThymeleafAutoConfiguration.class})
public class EndtermProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndtermProjectApplication.class, args);
    }

}
