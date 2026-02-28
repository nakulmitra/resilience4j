# Microservices Communication with RestTemplate, Circuit Breaker & Retry (Spring Boot)

This project demonstrates how multiple microservices communicate with each other using **RestTemplate**, and how to handle failures gracefully using **Circuit Breaker** and **Retry** mechanisms with **Resilience4j** in Spring Boot.

It is designed for **intermediate-level developers** who want to understand:

* Service-to-service communication
* Handling partial failures
* Implementing Retry mechanism
* Implementing Circuit Breaker pattern
* Preventing cascading failures in microservices

## Architecture Overview

This repository contains **3 Microservices**:

1. **Graduation Service** (Port: 8081)
2. **Company Service** (Port: 8082)
3. **User Service** (Port: 8080)

### How They Work Together

* The **User Service** acts as an aggregator.
* It calls:

  * Graduation Service
  * Company Service
* Uses:

  * `RestTemplate` for HTTP communication
  * `@Retry` for retry mechanism
  * `CircuitBreakerFactory` for circuit breaker implementation

## Microservices Flow

```
        Client
          |
          V
     User Service
    |           |
    V           V
Graduation     Company
Service        Service
```

If:

* Graduation Service fails -> Retry mechanism triggers
* Company Service fails repeatedly -> Circuit Breaker opens

## Technologies Used

* Java 8+
* Spring Boot
* Spring Web
* RestTemplate
* Resilience4j
* Spring Cloud Circuit Breaker
* Maven

## Retry Implementation (Graduation Service)

Retry is implemented using:

```
@Retry(name = "getGraduationData", fallbackMethod = "getGraduationDataFallback")
```

### How It Works:

* If Graduation Service fails
* It retries automatically based on configuration
* After max attempts -> fallback method executes

### Example from `IntermediateService`

```java
@Retry(name = "getGraduationData", fallbackMethod = "getGraduationDataFallback")
public Graduation getGraduationData(Integer id) {
    ResponseEntity<Graduation> graduationData = restTempalte
            .getForEntity("http://localhost:8081/graduation/getUserGraduationDataById/" + id, Graduation.class);
    return graduationData.getBody();
}
```

Fallback:

```java
public Graduation getGraduationDataFallback(Integer id, Exception ex) {
    return null;
}
```

---

## Circuit Breaker Implementation (Company Service)

Circuit Breaker prevents continuous calls to a failing service.

Implemented using:

```java
CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

return circuitBreaker.run(
    () -> restTempalte.getForObject(
        "http://localhost:8082/company/getUserCompanyDataById/" + id,
        Company.class
    ),
    throwable -> {
        return null;
    }
);
```

### Circuit Breaker States:

1. **CLOSED:** Normal state (calls allowed)
2. **OPEN:** Service failing, calls blocked
3. **HALF-OPEN:** Trial calls allowed

## Why Circuit Breaker is Important?

Without Circuit Breaker:

* Failing service keeps getting called
* Thread pool exhaustion
* Cascading failure across system

With Circuit Breaker:

* Fail fast
* Protect system stability
* Improve resilience

## How to Run the Project

### Step 1: Clone the Repository

```
git clone <your-repo-url>
```

### Step 2: Start Services in Order

1. Start Graduation Service (8081)
2. Start Company Service (8082)
3. Start User Service (8080)

### Step 3: Test Endpoints

Example:

```
http://localhost:8080/user/getUser/1
```

Now try:

* Stop Graduation Service -> Observe retry behavior
* Stop Company Service -> Observe circuit breaker behavior

## Logging Behavior

The `IntermediateService` maintains counters:

* `COMPANY_API_CALL_COUNT`
* `GRADUATION_API_CALL_COUNT`

This helps observe:

* Number of retries
* Number of actual calls
* When circuit breaker stops calls

## Failure Testing Scenarios

| Scenario                  | Expected Behavior             |
| ------------------------- | ----------------------------- |
| Graduation service down   | Retry attempts then fallback  |
| Company service down      | Circuit opens after threshold |
| Company service restarted | Circuit moves to HALF-OPEN    |

## Important Class: IntermediateService

This class:

* Makes REST calls
* Implements retry
* Implements circuit breaker
* Handles fallback methods
* Logs call count

## Learning Outcomes

After going through this project, you will understand:

* How microservices communicate
* Why retry alone is not enough
* How circuit breaker protects systems
* How to handle distributed failures
* How to build fault-tolerant systems

## Real-World Use Cases

* Payment gateways
* E-commerce checkout systems
* Banking systems
* High-traffic APIs
* Aggregator services

## Related Learning

If you're learning:

* [Java Tutorial Series](https://www.youtube.com/playlist?list=PLKrxcqbQdCgZDkAiCs6uGFK7yzhFkjNJU)
* [PostgreSQL Series](https://www.youtube.com/playlist?list=PLKrxcqbQdCga1o3NbaommIaQmqDJ_Y3Vo)
* [Linux tutorial series for software developers](https://www.youtube.com/playlist?list=PLKrxcqbQdCgbR2s0wxze6WE_OW07u_yJ8)
* [Real-World Projects with OOP Concepts](https://www.youtube.com/playlist?list=PLKrxcqbQdCgaAtUObt11xA63eO7kx4Epj)
* [Multithreading Series in Java](https://www.youtube.com/playlist?list=PLKrxcqbQdCgahnSGPIJehTKtUU_0kSevR)

## Stay Connected
* You can also check out my YouTube channel [Dev Portal](https://www.youtube.com/@DevPortal2114)
Subscribe for the latest tutorials.

* Connect with me [LinkedIn Profile](https://www.linkedin.com/in/nakul-mitra-microservices-spring-boot-java-postgresql/)

Happy coding! If you found these resources helpful, please give this repo a star ⭐ and share it with others who are learning.
