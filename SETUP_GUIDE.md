# 🚀 Quick Setup Guide for Beginners

## Step 1: Install PostgreSQL

1. Download from: https://www.postgresql.org/download/windows/
2. Run the installer
3. **IMPORTANT**: Remember the password you set (e.g., `postgres123`)
4. Keep port as `5432`
5. Install pgAdmin (comes with installer)

## Step 2: Create Database

1. Open **pgAdmin 4** from Windows Start Menu
2. Enter your master password
3. Expand: Servers → PostgreSQL
4. Right-click "Databases" → Create → Database
5. Name: `audit_db`
6. Click Save

## Step 3: Configure Project

Open `src/main/resources/application.yaml` and update:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/audit_db
    username: postgres
    password: YOUR_PASSWORD_HERE  # Change this!
```

## Step 4: Run the Application

### Option A: Using IDE (IntelliJ/Eclipse)
1. Open the project
2. Find `VirtusaApplication.java`
3. Right-click → Run

### Option B: Using Command Line
```bash
# Windows Command Prompt
cd audit-service-main
mvnw.cmd spring-boot:run
```

## Step 5: Test the APIs

### Using Browser (for GET requests):
- Get all records: http://localhost:8080/api/v1/records

### Using Postman or cURL:

**Create a record:**
```bash
curl -X POST http://localhost:8080/api/v1/records ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"My First Record\",\"description\":\"Testing\",\"status\":\"ACTIVE\"}"
```

**Get all records:**
```bash
curl http://localhost:8080/api/v1/records
```

**Update a record (ID 1):**
```bash
curl -X PUT http://localhost:8080/api/v1/records/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"Updated Title\",\"description\":\"Updated\",\"status\":\"INACTIVE\"}"
```

**Delete a record (ID 1):**
```bash
curl -X DELETE http://localhost:8080/api/v1/records/1
```

## Troubleshooting

### Error: "Connection refused"
- Make sure PostgreSQL is running
- Check if port 5432 is correct
- Verify username/password in application.yaml

### Error: "Port 8080 already in use"
- Change port in application.yaml: `server.port: 8081`

### Error: "Could not find or load main class"
- Run: `mvnw.cmd clean install`
- Then try running again

## What You've Built

✅ Complete CRUD REST API  
✅ PostgreSQL database integration  
✅ Hibernate ORM with entity mapping  
✅ Proper error handling  
✅ Input validation  
✅ Logging and monitoring  

## For Tomorrow's Review

Key points to mention:
1. "We have a Spring Boot microservice with full CRUD operations"
2. "PostgreSQL database with Hibernate ORM"
3. "5 REST APIs: Create, Read All, Read One, Update, Delete"
4. "Proper layered architecture with Controller-Service-Repository pattern"
5. "Added validation, error handling, and auto-timestamps"

Good luck! 🎉
