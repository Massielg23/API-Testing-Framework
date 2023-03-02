# API-Testing-Framework

## Description
This is a REST API testing framework created for the company YellowPaper as part of my assessment process. Therefore, it is meant for evaluation purposes only and I do not authorize it to be used in a different way. 

## Preconditions
In order to run the testing framework, you must have the following tools installed in your local environment:
- Docker Desktop.
- Java 19.
- Maven 3.9.0.
- IntelliJ IDE
- Git.

## Architecture
This REST API testing framework has been based on a simple and straightforward structure where you will find two layers only, the logic layer where classes contain the functions to execute the different REST API methods and the testing layer where the tests call the functions mentioned above and perform assertions to ensure the expected behavior of the APIs. These layers are the ones provided by Java by default.

In summary, Maven is the packages and dependencies manager which provides us with the libraries and packages needed. TestNG is the test runner that runs the different tests and generates the reports, RestAssured is the library that makes the API calls and assertions and Java, is the main programming language.

I decided to use this architecture and tools because of its simplicity, scalability, ease of use, and performance.
As you might notice, it is pretty simple and fast to automate the tests and get onboard on it  You do not need a lot of time to understand it on get used to it due to its simplicity, and they give us all we need to do a good verification process.

## Guide to use it

Once you have all the preconditions installed in your local environment, follow these instructions using your terminal or command-line:

- Clone the repo: https://github.com/swagger-api/swagger-petstore which contains the Swagger API service we are going to test:
    
    `git clone https://github.com/swagger-api/swagger-petstore.git`


- Go to the repo root folder to build and run the docker Container with the image:
  
  `cd ./swagger-petstore/`

  `docker build -t swaggerapi/petstore3:unstable .`

  `docker pull swaggerapi/petstore3:unstable`

  `docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable`



- Clone the repo https://github.com/Massielg23/api-testing-framework which contains the testing framework we are going to use:

    `git clone https://github.com/Massielg23/api-testing-framework.git`


- Go to the repo root folder and execute the automated tests:

    `cd ./api-testing-framework/`

    `mvn clean test`


- Open the report by clicking on the `index.html` file located in: api-testing-framework/target/surefire-reports/index.html

## Explanation of the Solution

### src/main/java/org.example.requests/
This is the logic layer. In this layer you will find classes that contain functions that return RestAssured response objects. These objects contain the status code, headers, and response body of the API request execution.

### src/test/java/
This is the testing layer. In this layer you will find the test classes where tests call the functions from the logic layer and perform verifications (assertions) against the Rest Assured object returned.

### src/test/resources/TestDesign.xlsx

This Excel file contains the test cases designed to be executed.

## Author: Massiel Gomez