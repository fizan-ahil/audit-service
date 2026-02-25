# Audit Service


A robust, production-ready **Spring Boot microservice** for managing audit records.  
This project demonstrates a clean layered architecture, modern Java practices, validation, and database integration suitable for enterprise systems.



### 🚀 Features

- **RESTful APIs**  
  Clean, versioned endpoints following REST best practices

- **Data Persistence**  
  PostgreSQL integration using Spring Data JPA and Hibernate

- **DTO Pattern**  
  Clear separation between API models and persistence entities

- **Security**  
  Spring Security configured (currently permit-all for POC purposes)

- **Monitoring & Health Checks**  
  Spring Boot Actuator for application health and metrics

- **Centralized Error Handling**  
  Global exception handling for consistent API responses

- **Modern Java**  
  Built with Java 21 features and best practices



### 🛠️ Tech Stack

- **Framework:** Spring Boot (parent v4.0.3)
- **Language:** Java 21
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Libraries & Tools:**
  - Spring Data JPA
  - Spring Security
  - Spring Boot Actuator
  - Entity Mapping
  - Hibernate ORM
  - Lombok



### 📁 Project Structure

src/
└── main/
    ├── java/
    │   └── com/virtusa/poc/
    │       ├── VirtusaApplication.java
    │       ├── config/
    │       ├── controller/
    │       ├── dto/
    │       ├── entity/
    │       ├── exception/
    │       ├── repository/
    │       └── service/
    └── resources/
        ├── application.yaml
