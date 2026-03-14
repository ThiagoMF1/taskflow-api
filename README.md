# TaskFlow API

![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Git](https://img.shields.io/badge/Git-Version_Control-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?style=for-the-badge&logo=github&logoColor=white)

TaskFlow API is a backend project built to practice Java and Spring Boot by creating a task management system with authentication, business rules, comments, dashboard metrics, and API documentation.

This project is being developed as part of my backend portfolio, with the goal of building something closer to a real system instead of just a basic CRUD.

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

## Project Status

This project is currently in development.

Current progress:
- initial project setup
- package structure organization
- Spring Boot application running
- GitHub repository created
- initial README created

## Technologies Planned for the Next 5 Days

- Java
- Spring Boot
- Spring Security
- PostgreSQL
- JWT
- Maven
- Swagger
- Git and GitHub

## Planned Features

### Authentication
- register user
- login user
- JWT authentication
- protected route to get current user

### Tasks
- create task
- list tasks
- get task by id
- update task
- delete task
- mark task as in progress
- mark task as done

### Business Rules
- status flow: TODO, IN_PROGRESS, DONE
- priority levels: LOW, MEDIUM, HIGH
- only owner or admin can update/delete some tasks
- filters by status and priority

### Comments
- add comment to task
- list comments by task

### Dashboard
- total tasks
- completed tasks
- pending tasks
- tasks by priority

### Documentation
- Swagger / OpenAPI

## Project Structure

```bash
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
Run the Project

On Windows PowerShell:

.\mvnw.cmd spring-boot:run

On Linux / Mac:

./mvnw spring-boot:run
Author

Thiago Machado Freire

GitHub: ThiagoMF1

Notes

More features and improvements will be added as the project evolves.
