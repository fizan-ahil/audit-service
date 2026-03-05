# API Testing Guide

## Using Postman

### 1. Create a Record (POST)
- **URL**: `http://localhost:8080/api/v1/records`
- **Method**: POST
- **Headers**: `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "title": "First Audit Record",
  "description": "This is my first test record",
  "status": "ACTIVE"
}
```

### 2. Get All Records (GET)
- **URL**: `http://localhost:8080/api/v1/records`
- **Method**: GET

### 3. Get Single Record (GET)
- **URL**: `http://localhost:8080/api/v1/records/1`
- **Method**: GET

### 4. Update Record (PUT)
- **URL**: `http://localhost:8080/api/v1/records/1`
- **Method**: PUT
- **Headers**: `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "title": "Updated Record Title",
  "description": "This record has been updated",
  "status": "INACTIVE"
}
```

### 5. Delete Record (DELETE)
- **URL**: `http://localhost:8080/api/v1/records/1`
- **Method**: DELETE

---

## Using Windows Command Prompt (cURL)

### 1. Create Record
```cmd
curl -X POST http://localhost:8080/api/v1/records ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"Test Record\",\"description\":\"Testing API\",\"status\":\"ACTIVE\"}"
```

### 2. Get All Records
```cmd
curl http://localhost:8080/api/v1/records
```

### 3. Get Single Record (ID: 1)
```cmd
curl http://localhost:8080/api/v1/records/1
```

### 4. Update Record (ID: 1)
```cmd
curl -X PUT http://localhost:8080/api/v1/records/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"Updated\",\"description\":\"Modified record\",\"status\":\"INACTIVE\"}"
```

### 5. Delete Record (ID: 1)
```cmd
curl -X DELETE http://localhost:8080/api/v1/records/1
```

---

## Expected Responses

### Success Responses:
- **POST** (Create): `201 Created` with record data
- **GET** (All): `200 OK` with array of records
- **GET** (Single): `200 OK` with single record
- **PUT** (Update): `200 OK` with updated record
- **DELETE**: `204 No Content`

### Error Responses:
- **404 Not Found**: Record doesn't exist
- **400 Bad Request**: Invalid input (missing title, etc.)
- **500 Internal Server Error**: Database connection issues

---

## Testing Flow

1. **Create** 3 records
2. **Get All** to see all 3 records
3. **Get Single** record by ID
4. **Update** one record
5. **Delete** one record
6. **Get All** again to verify deletion

This tests the complete CRUD functionality!
