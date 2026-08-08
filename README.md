# 🚀 SmartBank AI — Event-Driven Anomaly Detection Microservices

A production-grade, event-driven banking backend that integrates a **Java Spring Boot** transaction core with an asynchronous **Python AI Worker** for real-time fraud and anomaly detection via **CloudAMQP (RabbitMQ)**.

---

## 🌐 Live System Endpoints

* **Java Core API:** [https://smartbank-ai-microservices.onrender.com](https://smartbank-ai-microservices.onrender.com)
* **Live Event Trigger:** [https://smartbank-ai-microservices.onrender.com/1/analyze](https://smartbank-ai-microservices.onrender.com/1/analyze)
* **Python AI Worker:** Hosted asynchronously on Render (CloudAMQP Listener)

---

## 🏗️ System Architecture

```text
[ Client / Browser ]
        │
        ▼ (HTTP GET / POST)
┌───────────────────────────────────────┐
│     Java Spring Boot Microservice     │
│  - Manages Account & Transaction Logic│
│  - Converts payload & publishes event │
└──────────────────┬────────────────────┘
                   │ (AMQP / Port 5671)
                   ▼
┌───────────────────────────────────────┐
│       CloudAMQP (RabbitMQ Broker)     │
│  - Queue: `fraud_queue`               │
└──────────────────┬────────────────────┘
                   │ (Asynchronous Event Consume)
                   ▼
┌───────────────────────────────────────┐
│       Python AI Worker Service        │
│  - Scikit-learn (Isolation Forest)    │
│  - Evaluates Fraud Risk & Anomaly Score│
└───────────────────────────────────────┘

⚡ Key Features & Highlights
Asynchronous Event-Driven Architecture: Decoupled Spring Boot producer and Python consumer via RabbitMQ, ensuring zero blocking on banking transaction threads.

Real-Time ML Anomaly Detection: Python worker processes incoming queue events through machine learning models to detect fraudulent behavior patterns.

Fault-Tolerant Queue Management: Uses CloudAMQP over TLS for reliable message delivery and queuing during peak loads.

Cloud-Native Deployment: Containerized microservices deployed on Render with automated cron monitoring.

🤖 Machine Learning & Data Science Stack
Algorithm: Isolation Forest (Unsupervised Anomaly Detection)

Isolates transaction anomalies by randomly selecting feature splits, scoring outliers based on path length tree depth.

Data Pipelines: Pandas, NumPy for feature preprocessing and transaction log evaluation.

Libraries: scikit-learn, pika (RabbitMQ integration), bottleneck.

🛠️ Tech Stack
Java Core: Java 17, Spring Boot 3, Spring AMQP, JPA / Hibernate

AI Service: Python 3.12, Scikit-learn, Pandas, Pika

Messaging Broker: RabbitMQ (CloudAMQP)

DevOps & Hosting: Docker, Render, Git, Cron-Job.org

🧪 How to Test the Live API
1. Trigger via Web Browser
Open the following URL in your browser:

Plaintext
[https://smartbank-ai-microservices.onrender.com/1/analyze](https://smartbank-ai-microservices.onrender.com/1/analyze)

2. Trigger via cURL / Terminal
curl -X GET [https://smartbank-ai-microservices.onrender.com/1/analyze](https://smartbank-ai-microservices.onrender.com/1/analyze)

💻 Local Setup & Execution

Clone the Repository:
git clone [https://github.com/Srivastavasparsh/SmartBank-AI-Microservices.git](https://github.com/Srivastavasparsh/SmartBank-AI-Microservices.git)
cd SmartBank-AI-Microservices

Run the Java Microservice:
./mvnw spring-boot:run

Run the Python AI Worker:
cd ai-service
pip install -r requirements.txt
python fraud_service.py