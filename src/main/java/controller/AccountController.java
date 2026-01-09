package controller;

import com.securebank.app.entity.Account;
import com.securebank.app.service.AccountService;
import com.securebank.app.repository.AccountRepository; // Import Repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // Import RestTemplate
import java.util.Map; // Import Map

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // We inject the Repository to find users by ID
    @Autowired
    private AccountRepository accountRepository;

    // We inject RestTemplate to talk to Python
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account.getUsername(),
                account.getPassword(),
                account.getBalance());
    }

    // NEW AI ENDPOINT
    // NEW SAFE VERSION (Debug Mode)
    @GetMapping("/{id}/analyze")
    public String analyzeAccount(@PathVariable Long id) {
        try {
            // 1. Find account
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Account not found in Database"));

            // 2. call Python
            String pythonUrl = "http://127.0.0.1:5000/analyze";
            Map<String, Object> response = restTemplate.postForObject(pythonUrl, account, Map.class);

            // 3. Return result
            return "AI Analysis: " + response.get("analysis");

        } catch (Exception e) {
            // This will print the REAL error in Postman
            return "FAILED: " + e.getMessage();
        }
    }
}