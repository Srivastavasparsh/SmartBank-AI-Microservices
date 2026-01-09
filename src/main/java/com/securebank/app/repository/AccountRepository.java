package com.securebank.app.repository;

import com.securebank.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    Account findByUsername(String username);
}