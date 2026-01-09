package com.securebank.app.service;

import com.securebank.app.entity.Account;
import com.securebank.app.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account createAccount(String username, String password, Double initialBalance)
    {
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setBalance(initialBalance);
        return accountRepository.save(newAccount);
    }
}