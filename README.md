# Heroku UI Testing

This project provides UI tests for the [the-internet](https://the-internet.herokuapp.com) application using Selenide, Selenium, and JUnit. It includes a comprehensive suite of tests covering various pages and functionalities.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Docker Integration](#docker-integration)

## Features
- Comprehensive UI tests for `the-internet` pages (e.g., `/login`, `/shifting_content`, `/tables`, `/status_codes`, `/typos`).
- Page Object Model (POM) design pattern for maintainability.
- Integration with Selenide for simplified Selenium interactions.
- Allure reporting for test results.
- Docker support for running tests in a containerized environment.

## Prerequisites
- **Java 17** or higher
- **Maven** for dependency management
- **Docker** for containerized testing (optional)
- **Chrome Browser** (or another browser supported by Selenium WebDriver)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Grav3Lord/heroku-ui.git
   cd heroku-ui

2. Install dependencies using Maven:
   ```bash
   mvn clean install
   
3. Run all tests using Maven:
   ```bash
   mvn test
   ```
    If you want to run a specific test:
   ```bash
   mvn test -Dtest=LoginTests
   ```
   
## Docker-integration

1. Pull the Docker image:
   ```bash
   docker pull saucelabs/the-internet
   ```
   
2. Run the container:
   ```bash
    docker run -d -p 7080:7080 saucelabs/the-internet
   ```
   The application will be available at http://localhost:7080.
3. Update the base URL in utils/BaseDriver.java (if needed):
   ```java
   public static final String BASE_URL = "http://localhost:7080";
   ```
4. Run tests against the Dockerized application:
   ```bash
   mvn test
   ```
