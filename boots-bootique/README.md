# Boots-Bootique

An API that enables users to document their boot collection by creating, updating, retrieving, and deleting entries for boots. Users can seamlessly manage their boot collection, ensuring accurate and personalized logs of their boots.

## Technologies

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- H2 Database

## Features

- Create a new boot entry
- Retrieve a boot entry
- Update a boot entry
- Delete a boot entry
- Increment the quantity of a boot
- Decrement the quantity of a boot
- Search for boots by type, size, quantity, and material

## API Endpoints

- `GET /api/v1/boots`: retrieve all boots
- `GET /api/v1/boots/types`: retrieve all boot types
- `POST /api/v1/boots`: create a new boot entry
- `DELETE /api/v1/boots/{id}`: delete a boot entry by ID
- `PUT /api/v1/boots/{id}/quantity/increment`: increment the quantity of a boot by ID
- `PUT /api/v1/boots/{id}/quantity/decrement`: decrement the quantity of a boot by ID
- `GET /api/v1/boots/search?type={type}&size={size}&quantity={quantity}&material={material}`: search for boots by type, size, quantity, and material

## Future Optional Improvements

- Optimizing the search functionality to allow for more complex queries
