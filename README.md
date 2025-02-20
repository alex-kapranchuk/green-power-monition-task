# Project green-power-monition-task
## Project Overview
This project automates tests for a web application using Java, Playwright, Test NG, Maven, Allure, Log4j. The tests include Search and Product Cart and Shopping Cart Management.

### Technology Stack
- Java - Programming language
- Playwright - Browser automation tool
- TestNG - Testing framework
- Maven - Build automation tool
- Allure - Reporting tool
- Log4j - Logging

## Running Tests
1. Ensure all dependencies are installed:
```bash
mvn install
```
2. Run the tests:
```bash
mvn test
```
3. Generate Allure reports:
```bash
mvn allure:serve
```
### Logging Configuration
Logging is configured using Log4j. The configuration is stored in src/main/resources/log4j2.xml.

### Test Data Generation
Test data is generated using Instancio. Configuration and data generation are defined in the respective test classes.

### Documentation
Documentation for the tests is available in the Allure reports after running the tests.
