# springboot-ecommerce-microservices
A fully dockerized springboot microservices project that supports user registration and JWT authentication, inter-service communication, automatic service discovery, API documentation, caching and database migration support.

# Project execution
Simply start a docker engine on your machine and run docker compose up --build from inside the root folder of the project. Flyway will take care of creating tables and inserting data into them.
NOTE: For the users table only, registration in mandatory via API "http://localhost:8084/auth/v1/register" as passwords are encrypted during the registration process
All database migration scripts are under product-service\src\main\resources\db\migration

# Project modules overview
# api-gateway-service
Uses spring-cloud-gateway routing to map routes to different services.
Common entry point for all http requests (port 8084)
Implements JWT filter chain to authenticate incoming requests  

# auth-service
User registration and authentication service
Issues JWT token to valid users

# eureka-service
Starts an automatic service-discovery server to locate micro-services

# product-service
Service to create/read/update/delete products and product_categories

# review-service
Service to create/read/update/delete reviews

# Working screenshots
Host ports are mapped to docker container ports

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/68f313c8-4d54-45d7-9d2e-8c04d2499596" />


service discovery and registration with eureka server

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/c324d90a-1299-4798-b98d-fefdfd1596ca" />

user authentication and JWT token issuance

productdb=# select * from users;
 id |         created_at         |      email       |                           password                           | role |         updated_at         | username 
----+----------------------------+------------------+--------------------------------------------------------------+------+----------------------------+----------
  1 | 2025-09-15 21:41:59.957609 | john@gmail.com   | $2a$10$SobR1OYzKDArAkoALeCxve2n/zTs9NRiPF2uplw8i5Smh1D8gR8/m | user | 2025-09-15 21:41:59.957609 | john
  2 | 2025-09-15 21:42:20.29622  | mike@outlook.com | $2a$10$2ZvocPDBaUNTI3C3w.mPYe.Tb0cFn0fySBipNKAhSBziTSaIiIde. | user | 2025-09-15 21:42:20.29622  | mike

we will use either user to log in:- issues a JWT that will be passed to subsequent http request as a bearer token

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/cd315afe-28e9-4d94-8061-d07e7a987a6e" />


GET request to "http://localhost:8084/product/v1/products"


<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/3043d9dc-a315-4383-9a7c-bedf34a68c39" />


POST request to "http://localhost:8084/review/v1/review/create"


<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/2761e76b-b4d1-4a63-b641-0292caa5f0c7" />

# caching with redis
127.0.0.1:6379> KEYS *
1) "product::Basketball"
2) "products-all::[1,5,productName: ASC]"
127.0.0.1:6379>
   
# swagger API documentation at "http://localhost:8085/v3/api-docs"


<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/89ad0407-0f1d-4ae2-bcd1-4e8430d7873b" />










