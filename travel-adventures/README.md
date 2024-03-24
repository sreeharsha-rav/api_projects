# Travel Adeventures

An API that enables users to document their travel experiences by creating, updating, retrieving, and deleting entries for places visited. Users can seamlessly manage their travel memories, ensuring accurate and personalized logs of their journeys.

## Technologies

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- H2 Database

## Features

- Create a new travel log entry
- Retrieve a travel log entry
- Update a travel log entry
- Delete a travel log entry
- Validation for creating travel log entries

## API Endpoints

- `GET /traveladevntures`: retrieve all travel log entries
- `GET /traveladventures/bycountry/{country}`: retrieve all travel log entries for a specific country
- `GET /traveladventures/bystate?state={state}`: retrieve all travel log entries for a specific state
- `POST /traveladventures`: create a new travel log entry
- `GET /traveladventures/{id}`: retrieve a travel log entry by ID
- `PUT /traveladventures/{id}`: update a travel log entry by ID
- `DELETE /traveladventures/{id}`: delete a travel log entry by ID

## Setup

1. Run the application using the following command:
    ```shell
    ./mvnw spring-boot:run
    ```
2. Access the API using curl or Postman. Curl commands are provided in [curl-commands.txt](./curl-commands.txt).

## Future Optional Improvements

- Account appropriate API responses for possible API requests
- Validation for updating travel log entries
- Using validator spring feature to validate the request body
- Secure the API using Spring Security
- Add HTTPS support