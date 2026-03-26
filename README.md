```markdown
# BookStoreApiTestingFromGit

API test automation project for a Book Store service.

The project uses **Java 17**, **Maven**, **TestNG**, **Rest Assured**, **AssertJ**, **Lombok**, and **Allure** to validate REST API behavior and generate readable reports.

## Features

- API test automation for book store resources
- CRUD coverage for core endpoints
- Soft assertions for richer validation
- Dynamic test data generation
- Allure test reporting

## Tech Stack

- Java 17
- Maven
- TestNG
- Rest Assured
- AssertJ
- Allure
- Lombok
- Jackson
- JavaFaker

## Project Structure

- `src/main/java`
  - API clients and domain models
  - shared request/response utilities
  - test support helpers
- `src/test/java`
  - tests for authors and books
  - test base setup

## Test Coverage

### Authors
- Get all authors
- Get author by ID
- Get authors by book ID
- Create author
- Update author
- Delete author

### Books
- Get all books
- Get book by ID
- Create book
- Update book
- Delete book

## Requirements

- Java 17
- Maven 3.8+


## Run Tests

Run the full test suite:
```
bash
mvn clean test
```
## Generate Allure Report

Generate the report after test execution:
```
bash
mvn allure:report
```
Or start a local report server:
```
bash
mvn allure:serve
```
## Notes

- Tests are written with TestNG.
- Assertions use AssertJ and TestNG soft assertions.
- The project is intended for API validation and reporting, not for UI testing.

## Useful Maven Commands
```
bash
mvn clean test
mvn allure:report
mvn allure:serve
```
## License

No license has been specified yet.
```