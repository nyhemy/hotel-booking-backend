# Final Project

## Description
Project meant to simulate a real-world client, and is meant to test trainee proficiency with tools learned throughout the cycle training program.

## Getting Started

***Pre-Requisites***

- Java and Intellij

- A Git GUI, preferably GitBash

- Make sure that you have a postgres database available and configured with the following options:
	POSTGRES_USER=user
	POSTGRES_PASSWORD=root
	PORT=5432

The DataLoader class in the data package will load a few examples of each entity (Patient, Encounter) into the database after the service starts up.

## Running the application
* Look Under src\main\java\io\catalyte\training for AppRunner.java.
* If starting in Intellij right click AppRunner, then click run.
* Can also be run subsequently in the top right corner green play symbol.

## Postman
* The complete postman collection for this example can be found here: [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/d58e35d6cbb3468b7bcc)
* Demonstrates all of the 2XX and 4XX functional requirements for GET, POST, PUT, and DELETE methods for these entities: Patient, Encounter.

## Testing

***Running Mockito Unit Tests***
* Look Under src\test\java\io\catalyte\training for the services package
* Right click services and run tests in io.catalyte.training.services
* Code coverage can be viewed by right-clicking on the service package and selecting Run with Coverage.
* Coverage can also be run subsequently in the top right corner white and green shield symbol.
* Unit tests cover all services.

***Running Integration Tests***
* Start the container.
* Look Under src\test\java\io\catalyte\training for the controllers package.
* Right click controllers and run tests in io.catalyte.training.controllers.
* Integration tests cover payload, content type, and status code tests of all 2XX
* Code coverage can be viewed by right-clicking on the controller package and selecting Run with Coverage.
* Coverage can also be run subsequently in the top right corner white and green shield symbol.
* Real data is manipulated in my integration tests.

## Logging
* Use SLF4J and Log4j2 to log information to a local file as well as the console.
* Logging configuration specified in log4j2.xml file under resources.
* Currently logging to a rolling log file under C:\temp on the local drive
* Log file will be created as soon as application starts.
* Logger will write one line (INFO) for each method called in the controllers.
* Logger will log errors when exceptions are caught.

## Swagger
* Run application
* configurtion file for swagger is found in src\test\java\io\catalyte\training in the config package and is called SwaggerConfig. 
* Go to `localhost:8080/swagger-ui.html` to see swagger documentation for all endpoints.

## Linting
* This project uses Google's Java coding standards.
* You can lint any code block by pressing `CTRL + ALT + l`.