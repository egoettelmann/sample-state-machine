# Sample State Machine App

Sample project implementing a state machine.

## Requirements

Consider an infinite grid of white and black squares. The grid is initially all white and there is a machine in one cell facing right. It will move based on the following rules:
 - If the machine is in a white square, turn 90° clockwise and move forward 1 unit
 - If the machine is in a black square, turn 90° counter-clockwise and move forward 1 unit
 - At every move flip the color of the base square

Implement an application that will receive HTTP PUT requests with a number of steps the simulation should run, always starting from the same conditions, and output the resulting grid to a file.

## Usage

### Run the Stack

Simply start the application through Docker Compose:
```shell script
docker-compose up --build -V
```

This will build the image and run the app on port `8080`.

*Note*: During development, the application can also be run with:

```shell script
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Play around

You can use the Swagger interface to perform simulations on the app:
 - <http://localhost:8080/swagger-ui.html>

## Project overview

The application follows a [package-by-component](http://www.codingthearchitecture.com/2015/03/08/package_by_component_and_architecturally_aligned_testing.html) structure.

### Technical stack

 - The application uses SpringBoot
 - Rest error handling is done with `org.zalando:problem-spring-web`
 - Rest endpoints are described with `springdoc-openapi` (Swagger for OpenAPI v3 specs)
