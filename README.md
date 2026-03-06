# Audit Service



A robust, production-ready **Spring Boot microservice** for managing audit records.  
This project demonstrates a clean layered architecture, modern Java practices, validation, and database integration suitable for enterprise systems.



### 🚀 Features

- **RESTful APIs**  
  Clean, versioned endpoints following REST best practices
  - Create records (POST)
  - Get all records (GET)
  - Get record by ID (GET)
  - Update records (PUT)
  - Delete records (DELETE)

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

```
src/
└── main/
    ├── java/
    │   └── com/virtusa/poc/
    │       ├── config/
    │       ├── controller/
    │       ├── dto/
    │       ├── entity/
    │       ├── exception/
    │       ├── repository/
    │       └── service/
    └── resources/
        ├── application.yaml
```

---

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 12 or higher

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd audit-service-main
   ```

2. **Configure PostgreSQL**
   
   Create a database in PostgreSQL:
   ```sql
   CREATE DATABASE audit_db;
   ```

3. **Update application.yaml**
   
   Edit `src/main/resources/application.yaml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/audit_db
       username: your_postgres_username
       password: your_postgres_password
   ```

4. **Build and Run**
   ```bash
   # Using Maven wrapper
   ./mvnw spring-boot:run
   
   # Or using Maven
   mvn spring-boot:run
   ```

5. **Verify the application**
   
   The application will start on `http://localhost:8080`

---

