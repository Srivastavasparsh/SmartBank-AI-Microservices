package com.securebank.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.securebank.app.service.AccountService;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    // This runs automatically exactly once when the server boots!
    @Bean
    CommandLineRunner runTest(AccountService accountService) {
        return args -> {
            System.out.println("--- FIRING TEST RABBITMQ EVENT ---");
            accountService.publishTransactionEvent("user_99", 45000.0, 500.0, 3.5);
        };
    }
}