package com.securebank.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.securebank.app.entity.Account;
import com.securebank.app.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // FIX: We build the ObjectMapper ourselves instead of relying on Spring!
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired(required = false)
    private AccountRepository accountRepository;

    // Method expected by AccountController
    public Account createAccount(String username, String accountType, double balance) {
        Account account = new Account();
        if (accountRepository != null) {
            return accountRepository.save(account);
        }
        return account;
    }

    // Asynchronous Event Publisher for AI Fraud Detection
    public void publishTransactionEvent(String userId, double amount, double distanceKm, double timeOfDay) {
        try {
            // 1. Create Event Payload
            Map<String, Object> event = new HashMap<>();
            event.put("user_id", userId);
            event.put("amount", amount);
            event.put("distance_km", distanceKm);
            event.put("time_of_day_hours", timeOfDay);

            // 2. Convert to JSON String
            String jsonMessage = objectMapper.writeValueAsString(event);
            
            // 3. Drop event into RabbitMQ queue instantly
            rabbitTemplate.convertAndSend("transaction_events", jsonMessage);
            
            System.out.println("🚀 Asynchronous Event Published: Transaction Sent to AI for Analysis.");
            
        } catch (Exception e) {
            System.out.println("Failed to publish event: " + e.getMessage());
        }
    }
}