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

## 📡 API Endpoints

### Base URL: `http://localhost:8080/api/v1/records`

### 1. Create a Record
```http
POST /api/v1/records
Content-Type: application/json

{
  "title": "Sample Record",
  "description": "This is a test record",
  "status": "ACTIVE"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "title": "Sample Record",
  "description": "This is a test record",
  "status": "ACTIVE",
  "createdAt": "2026-03-04T10:30:00",
  "updatedAt": "2026-03-04T10:30:00"
}
```

### 2. Get All Records
```http
GET /api/v1/records
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Sample Record",
    "description": "This is a test record",
    "status": "ACTIVE",
    "createdAt": "2026-03-04T10:30:00",
    "updatedAt": "2026-03-04T10:30:00"
  }
]
```

### 3. Get Record by ID
```http
GET /api/v1/records/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Sample Record",
  "description": "This is a test record",
  "status": "ACTIVE",
  "createdAt": "2026-03-04T10:30:00",
  "updatedAt": "2026-03-04T10:30:00"
}
```

### 4. Update a Record
```http
PUT /api/v1/records/1
Content-Type: application/json

{
  "title": "Updated Record",
  "description": "Updated description",
  "status": "INACTIVE"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Updated Record",
  "description": "Updated description",
  "status": "INACTIVE",
  "createdAt": "2026-03-04T10:30:00",
  "updatedAt": "2026-03-04T11:45:00"
}
```

### 5. Delete a Record
```http
DELETE /api/v1/records/1
```

**Response (204 No Content)**

---

## 🧪 Testing with cURL

```bash
# Create a record
curl -X POST http://localhost:8080/api/v1/records \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Record","description":"Testing","status":"ACTIVE"}'

# Get all records
curl http://localhost:8080/api/v1/records

# Get record by ID
curl http://localhost:8080/api/v1/records/1

# Update a record
curl -X PUT http://localhost:8080/api/v1/records/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated","description":"Modified","status":"INACTIVE"}'

# Delete a record
curl -X DELETE http://localhost:8080/api/v1/records/1
```

---

## 📝 Database Schema

The application automatically creates the following table:

```sql
CREATE TABLE records (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
```
