# Parsing Service
This Java-based service is supported by a REST API for storing and processing exchange rates.
Note. This service was created as a test task for an internship at one of the IT companies.

## Features
The service allows you to receive and parse information from the public website https://kurs.kz/
Parsing is carried out on the front side of the application every 5 minutes. The main page you will find the page in the folder front\index.html

## Technologies Used
The User Transaction Service is built using the following technologies:

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Javascript
* Thymeleaf
* Bootstrap

## Prerequisites
Before running the Service, you will need to have the following software installed on your machine:

* Java Development Kit (JDK) 17 or higher
* PostgreSQL

## Getting Started
To run the Service locally, follow these steps:

1. Clone the repository to your local machine:
```bash
git clone https://github.com/BRuslanB/ParsingService.git
```
2. Create a database in PostgreSQL:
``` 
currency_db
```
3. Build and run the Java application
```bash
java -jar ./buld/libs/ParsingService-0.0.1-SNAPSHOT.jar
```
4. Open the page.Temporarily disable CORS policy in your browser first.
``` 
front\index.html
``` 

## Contributing
If you would like to contribute to developing this Service, please submit a pull request or open an issue on the GitHub repository.

## License
This Service is licensed under the MIT License. See the LICENSE file for more details.