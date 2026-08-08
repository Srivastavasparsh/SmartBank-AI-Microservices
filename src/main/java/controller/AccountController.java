package com.securebank.app.controller;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.app.repository.AccountRepository;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // Injecting the RabbitMQ template to send messages to the cloud
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}/analyze")
    public String analyzeAccount(@PathVariable Long id) {
        try {
            // Create a mock transaction payload to bypass the empty database
            String mockTransaction = "{\"accountId\": " + id + ", \"amount\": 1500.00, \"location\": \"Kanpur\"}";

            // Publish the mock data directly to the RabbitMQ queue
            rabbitTemplate.convertAndSend("fraud_queue", mockTransaction);

            // Return immediate success
            return "✅ SUCCESS! Mock transaction for Account " + id + " has been published to RabbitMQ!";

        } catch (Exception e) {
            return "FAILED: " + e.getMessage();
        }
    }
}