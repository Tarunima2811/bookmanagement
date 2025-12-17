# **Book Management System**

A simple Spring Boot application for managing books (CRUD operations) using PostgreSQL as the database. This project demonstrates RESTful APIs, JPA integration, and basic application configuration. This is a lightweight backend application designed with an easy Docker containerization, allowing it to run seamlessly in any environment.

## Features

1. Find all books (with Pagination & Sorting)
2. Create, update, and soft delete books 
3. Search for books using partial title or author name 
4. Search for book using any parameters
5. RESTful API design 
6. Spring Boot + Spring Data JPA 
7. PostgreSQL database integration 
8. Validation and exception handling 
9. Logging with appropriate messages and HTTP Response codes 
10. Layered architecture (Controller, Service, Repository)
11. Multi-container application (using Docker)

## Tech Stack

Java: openjdk:27

Spring Boot: 3.5

Spring Data JPA

Hibernate: 6.5

PostgreSQL

Maven

Lombok

Docker

## REST API Endpoints

1. GET - FIND ALL BOOKS:
   postman request 'localhost:8080/bookmanagement/all?page=0&size=10' \
   --body ''
2. GET - GET BOOK BY ID:
   postman request 'localhost:8080/bookmanagement/book/4222515c-8949-47dd-b702-f5ee89e68aec'
3. GET - GET BOOK CONTAINING TITLE:
   postman request 'localhost:8080/bookmanagement/bookByTitle?bookTitle=Harry%20Potter'
4. GET - GET BOOK BY AUTHOR:
   postman request 'localhost:8080/bookmanagement/bookByAuthor?bookAuthor=Blyton'
5. POST - SEARCH
   postman request POST 'localhost:8080/bookmanagement/search' \
   --header 'Content-Type: application/json' \
   --body '{
   "bookTitle": "Arabian",
   "bookLanguage": "Arabic"
   }'
6. POST - ADD OR UPDATE BOOK: 
   postman request POST 'localhost:8080/bookmanagement/addOrUpdateBook' \
   --header 'Content-Type: application/json' \
   --body '{
   "bookTitle": "Harry Potter & the Prisoners of Azkaban",
   "bookAuthor": "J. K. Rowling",
   "bookLanguage": "English",
   "bookGenre": "Fiction",
   "bookPublisher": null,
   "bookPublishDate": "2002-05-05T00:00:00.000+00:00"
   }' 
7. DELETE - DELETE BOOK BY ID:
   postman request DELETE 'localhost:8080/bookmanagement/delete/5b77769e-784d-446e-a870-7dcfcc004e42'

## Running the Application

Assumption: Docker should be installed and running

1. Clone the repository

    git clone https://github.com/Tarunima2811/bookmanagement.git

2. Import project to your IDE

3. Create .env file in root directory with following content:

   DB_USER=your db username

   DB_PASSWORD=your db password

4. Install Maven dependencies

   mvn clean install

5. Create jar 

   mvn clean package

6. Run application using docker-compose file
    
    docker-compose up --build

7. Test application APIs using cURLs mentioned above

8. Stop application and remove containers
    
    docker-compose down

## Future Enhancements

Swagger/OpenAPI documentation

Authentication & authorization (JWT)

Rate Limiting to protect API from abuse

Caching of frequently queried books

Creation of DTOs so that Entity is not exposed

More elaborate data model
