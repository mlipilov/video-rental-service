# Video Rental Service

## Table of Contents
- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Decisions Made](#decisions-made)
- [Focus Areas](#focus-areas)
- [Non-Focus Areas](#non-focus-areas)
- [How to Run](#how-to-run)
- [Using the Program](#using-the-program)

## Introduction

This repository contains the source code for the Video Rental Service application. The application allows users to rent and return movies, and it calculates the rental fees based on different criteria.

## Project Structure

- `src/main/java/com/casumo/videorentalservice/`: Contains the main application code.
    - `business/rental/`: Handles movie rental logic.
    - `business/returnal/`: Handles movie return logic.
    - `model/entity/`: Contains domain entities and models.
    - `persistence/`: Contains Spring Data JPA repositories.
    - `presentation/`: Contains REST controllers.
- `src/test/java/com/casumo/videorentalservice/`: Contains unit and integration tests.
- `README.md`: This file.

## Decisions Made

1. **Framework and Libraries**:
    - **Spring Boot**: For rapid application development and dependency management.
    - **Spring Data JPA**: To simplify data access and manipulation.
    - **Lombok**: To reduce boilerplate code for model and data classes.
    - **TestContainers** - to be on the same page with our cloud env
    - **Junit5** - the most known unit test framework with basically everything we need to write unit tests

2. **Database**:
    - **PostgreSQL**: Chosen as the one of the most popular and reliable DB which is fully satisfies projects needs

3. **Testing**:
    - Focused on creating meaningful unit and integration tests to ensure code reliability.

4. **Architecture**:
    - Followed the layered architecture pattern to separate concerns and improve maintainability.

## Focus Areas

- **Domain-Driven Design**: Ensured the domain models accurately represent the business logic and rules.
- **RESTful API Design**: Created a well-structured and versioned API to interact with the frontend.
- **Error Handling**: Implemented simple error handling to provide feedback to the users and developers.
- **Extensibility**: Designed the application with extensibility in mind, making it easier to add new features or modify existing ones.

## Non-Focus Areas

- **Front-End Development**: Focused primarily on the backend service. Only minimalistic endpoints for testing and interaction.
- **Advanced Security**: Basic security configurations were considered, but advanced measures such as token-based authentication were not the main focus due to the scope of the initial implementation.
- **Observability**: Was skipped to concentrate on the business logic as the task requires.

## How to Run

1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd video-rental-service
    ```

2. **Build the project**:
    ```sh
    ./mvnw clean install
    ```

3. **Run the application**:
    ```sh
    ./mvnw spring-boot:run
    ```

   The application will start on `http://localhost:8080`.

## Using the Program

1. **Rent a Movie**:
    - Endpoint: `POST /rentals`
    - Request Body:
      ```json
      {
        "rentalDays": 1,
        "movieId": 12
      }
      ```

2. **Return a Movie**:
    - Endpoint: `POST /returns`
    - Request Body:
      ```json
      [12]
      ```