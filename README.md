# SmartBank AI System 🏦 🤖

A hybrid banking application that integrates a **Java Spring Boot** backend with a **Python AI Microservice**.

The system handles secure banking transactions in Java while offloading complex credit score analysis to a Python-based AI service, communicating via RESTful APIs.

## 🚀 Key Features
* **Hybrid Architecture:** Seamless integration between Java (Business Logic) and Python (Data Logic).
* **Microservices Communication:** Uses HTTP REST requests to bridge the two languages.
* **Credit Scoring Engine:** Python service calculates creditworthiness based on account balance and history.
* **Secure Transactions:** Java backend manages user accounts and balance updates.

## 🛠️ Tech Stack
* **Backend Core:** Java, Spring Boot
* **AI Service:** Python, Flask
* **Communication:** REST API (JSON)
* **Tools:** Maven, Git, IntelliJ IDEA

## ⚙️ How It Works
1.  **User Request:** The frontend (or Postman) sends a request to the Java Backend.
2.  **Processing:** Java handles the standard banking logic.
3.  **AI Analysis:** Java sends a specific payload to the Python Flask service.
4.  **Response:** Python processes the logic and returns a JSON response to Java.

## 📦 How to Run
**1. Start the AI Service (Python):**
```bash
cd ai-service
python app.py