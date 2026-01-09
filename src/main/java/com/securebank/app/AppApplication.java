package com.securebank.app;

import com.securebank.app.entity.Account;
import com.securebank.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@SpringBootApplication
@RestController
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Autowired
	private AccountRepository accountRepository;

	// 1. Create User "Sarthak" Automatically
	@Bean
	public CommandLineRunner run(AccountRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				Account user = new Account();
				user.setUsername("Sarthak");
				user.setPassword("secure123");
				user.setBalance(5000.0);
				repository.save(user);
				System.out.println("✅ USER SARTHAK CREATED!");
			}
		};
	}

	// 2. The AI Endpoint (Returning JSON Map)
	@GetMapping("/check-ai")
	public Map<String, Object> checkAi() {
		try {
			// Get the user
			Account user = accountRepository.findById(1L)
					.orElseThrow(() -> new RuntimeException("User not found"));

			// Create the tool HERE (Manually) to prevent the crash
			RestTemplate restTemplate = new RestTemplate();

			// Call Python (Port 5001 for Mac compatibility)
			String pythonUrl = "http://127.0.0.1:5001/analyze";

			// Call Python and return the full JSON result
			return restTemplate.postForObject(pythonUrl, user, Map.class);

		} catch (Exception e) {
			return Map.of("analysis", "Error", "recommendation", e.getMessage());
		}
	}
}