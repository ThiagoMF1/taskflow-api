# TaskFlow API

<p align="left">
  <img src="https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Spring%20Security-6.x-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security" />
  <img src="https://img.shields.io/badge/PostgreSQL-18-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/JWT-Auth-black?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT" />
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger" />
  <img src="https://img.shields.io/badge/Git-Version%20Control-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git" />
  <img src="https://img.shields.io/badge/GitHub-Repository-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />
</p>

TaskFlow API is a backend project built to practice Java and Spring Boot by creating a task management system with authentication, JWT security, protected routes, dashboard metrics, and clean API structure.

This project is being developed as part of my backend portfolio, with the goal of building something closer to a real system instead of just a basic CRUD.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven
- Lombok
- Bean Validation
- JWT
- Swagger / OpenAPI
- Git and GitHub

---

## Project Status

This project is currently in development.

Current progress:

- project setup completed
- PostgreSQL configured
- user entity created
- authentication with JWT implemented
- protected routes working
- authenticated user endpoint implemented
- task CRUD implemented
- task dashboard implemented
- global exception handling implemented
- Swagger documentation working

---

## Implemented Features

### Authentication

- register user
- login user
- JWT token generation
- protected route authentication
- get authenticated user with `/api/auth/me`

### Tasks

- create task
- list authenticated user tasks
- get task by id
- update task
- update task status
- delete task

### Dashboard

- total tasks
- pending tasks
- in progress tasks
- completed tasks

### Error Handling

- global exception handling
- duplicate email returns `409 Conflict`
- task not found returns `404 Not Found`
- validation errors return `400 Bad Request`

### Documentation

- Swagger / OpenAPI

---

## Task Status

The project currently supports these task statuses:

- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`

---

## Task Priority

The project currently supports these task priorities:

- `LOW`
- `MEDIUM`
- `HIGH`

---

## Main Endpoints

### Auth

- `POST /api/auth/register` → register a new user
- `POST /api/auth/login` → login and get JWT token
- `GET /api/auth/me` → get authenticated user data

### Tasks

- `POST /api/tasks` → create task
- `GET /api/tasks` → list authenticated user tasks
- `GET /api/tasks/{id}` → get task by id
- `PUT /api/tasks/{id}` → update task
- `PATCH /api/tasks/{id}/status` → update task status
- `DELETE /api/tasks/{id}` → delete task
- `GET /api/tasks/dashboard` → get task dashboard metrics

---

## Authentication

The API uses JWT authentication.

After login, the token must be sent in protected requests using this header:

```http
Authorization: Bearer your_token_here
Example Validation Error Response
{
  "timestamp": "2026-03-20T14:26:21.6005612",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "validationErrors": {
    "title": "Title is required",
    "priority": "Priority is required"
  }
}
Project Structure
src/main/java/com/thiagomf/taskflowapi
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── TaskflowapiApplication.java
Getting Started
Prerequisites

Make sure you have installed:

Java 21
PostgreSQL
Git
Clone the Repository
git clone https://github.com/ThiagoMF1/taskflow-api.git
Enter the Project Folder
cd taskflow-api
Create the Database
CREATE DATABASE taskflowdb;
Configure application.properties

Example:

spring.application.name=taskflow-api

spring.datasource.url=jdbc:postgresql://localhost:5432/taskflowdb
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-message=always
server.error.include-binding-errors=always

jwt.secret=taskflow-secret-key-for-development-only-2026
jwt.expiration=86400000
Run the Project

On Windows PowerShell:

.\mvnw spring-boot:run

On Linux / Mac:

./mvnw spring-boot:run
Swagger Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html
API Testing

You can test the API using:

Swagger UI
Postman
Next Steps

Planned next improvements:

task comments
more business rules
dashboard improvements
endpoint refinements
more documentation improvements
Author

Thiago Machado Freire

GitHub: ThiagoMF1
LinkedIn: Thiago Machado Freire
linkedin.com/in/thiago-machado-freire-779964281/
