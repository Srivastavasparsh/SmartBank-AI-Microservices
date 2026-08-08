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