<!-- Badges -->
<p align="center">
  <img src="https://img.shields.io/badge/SpringBoot-2.5-bluish?logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Microservices-architecture-purple" alt="Microservices" />
  <img src="https://img.shields.io/badge/Docker-Compose-blue?logo=docker&logoColor=white" alt="Docker Compose" />
  <img src="https://img.shields.io/badge/JWT-Authentication-green" alt="JWT Authentication" />
  <img src="https://img.shields.io/badge/Redis-Caching-red" alt="Redis Caching" />
  <img src="https://img.shields.io/badge/Flyway-Migration-teal" alt="Flyway" />
</p>

# 🛒 SpringBoot E-Commerce Microservices

A fully dockerized Spring Boot microservices project featuring user registration & JWT authentication, inter-service communication, automatic service discovery, caching, database migration, and more.

---

## ✅ Features

- **User registration** with encrypted passwords + **JWT authentication**
- **API Gateway** to route and secure requests via JWT filter chain 
- **Service Discovery** using Eureka 
- **Product Service**: CRUD operations for products and categories  
- **Review Service**: CRUD for reviews  
- **Caching** via Redis  
- **Database migration** & version control using Flyway  
- **Dockerized setup**: All microservices + gateway + discovery + supporting services can be built & run via Docker Compose
---

## 🏗 Architecture

```text
                                 +------------------------+
           +-------------------->   Eureka Service        |
           |                     |  (Service Discovery)    |
           |                     +------------------------+
           |
     +-----+------+              +-------------------+     +-----------------+
     | API Gateway +------------->  Auth Service      |     |  Product Service |
     | (Spring-Cloud) |           +-------------------+     +-----------------+
     |                |                                       |
     |                |                                       |
     |                |                                       |
     |                +------------> Review Service            |
     |                                                +--------+
     |
     +--> Redis (cache)

🚀 Getting Started
Prerequisites

Docker

Docker Compose

(Optional) Local Java & Maven if you want to run services without Docker

Setup & Run
# Clone the repo
git clone https://github.com/KalpajPatil/springboot-ecommerce-microservices.git
cd springboot-ecommerce-microservices

# Build & start all services
docker-compose up --build


The Flyway scripts will initialize / migrate the databases for the services. 
GitHub

The auth service requires registration first, since passwords are encrypted during registration. 

🔧 Endpoints & Usage
Service	HTTP Port	Sample Endpoints
API Gateway	8084	POST /auth/v1/register 
 • POST /auth/v1/login • GET /product/v1/products etc.

Product Service	internal via gateway	CRUD on /product/v1/… 

Review Service	internal	CRUD on /review/v1/… 

Swagger / API docs are made available via the relevant service(s). 

🗂 Project Structure
/
├── api-gateway-service/       # Spring Cloud Gateway & JWT routing
├── auth-service/              # Handles user registration, login, JWT
├── eureka-service/            # Service Discovery server
├── product-service/           # Products & product categories CRUD + DB migrations
├── review-service/            # Reviews CRUD + relation to products etc.
├── docker-compose.yml         # Orchestrates all containers
├── README.md
└── LICENSE

🔧 Technologies

Language: Java

Frameworks: Spring Boot, Spring Cloud Gateway, Spring Cloud Netflix Eureka

Security: JWT

DB & Migration: Postgres + Flyway 

Caching: Redis 

Containerization: Docker + Docker Compose
