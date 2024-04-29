# DiningReview API

This API is a simple API that allows users to create, read, update, and delete reviews for restaurants. The API is built using Spring Boot and uses H2 as the database.

## Technologies

- Java 17
- Spring Boot 3.2.4
- H2 Database
- Spring Data JPA

## Features

- Create, read, update, and delete users
- Create, read, update, and delete restaurants
- Create, read, update, and delete dining reviews
- Search for restaurants based on zip code and allergies
- Dining reviews can be accepted by an admin, which will update the restaurant rating

## Endpoints

### User Endpoints

- `POST /api/dining_review/users` - Create a new user
- `GET /api/dining_review/users/name/{displayName}` - Get a user by display name
- `PUT /api/dining_review/users/{id}` - Update a user by id
- `DELETE /api/dining_review/users/{id}` - Delete a user by id

### Admin Endpoints

- `POST /api/dining_review/admin/{id}` - Accept a dining review and update the restaurant rating, from given review id

### Restaurant Endpoints

- `POST /api/dining_review/restaurants` - Create a new restaurant
- `GET /api/dining_review/restaurants/{id}` - Get a restaurant by id
- `GET /api/dining_review/restaurants` - Get restaurants based on query parameters
  - `zipCode` - Filter by zip code
  - `allergy` - Filter by allergy
- `PUT /api/dining_review/restaurants/{id}` - Update a restaurant by id
- `DELETE /api/dining_review/restaurants/{id}` - Delete a restaurant by id

### Dining Review Endpoints

- `POST /api/dining_review/reviews` - Create a new dining review
- `GET /api/dining_review/reviews/{id}` - Get a dining review by id
- `PUT /api/dining_review/reviews/{id}` - Update a dining review by id
- `DELETE /api/dining_review/reviews/{id}` - Delete a dining review by id

## Running the API

- Clone the repository
- Run `mvn clean install` to build the project
- Run `mvn spring-boot:run` to start the API
- The API will be running on `http://localhost:8080`
- You can access the H2 console at `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:dining_review`
  - User Name: `sa`
  - Password: `password`
- Test the API using Postman or any other API testing tool