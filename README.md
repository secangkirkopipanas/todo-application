# Todo Application with Spring Boot 3.2.x

![spring boot todo application](./screenshot.png)

This is an ENTIRE application for Java Spring Boot built using:
- Spring Boot 3.2.x
- Spring Data JPA
- H2 Database (embedded) / PostgreSQL
- Thymeleaf

## Development instructions

- Clone the source code from Git repository
  ```
  git clone https://github.com/secangkirkopipanas/todo-application.git
  ```
  
- Change directory to the application directory
  ```
  cd todo-application
  ```

- Build the application
  ```
  ./mvnw clean package
  ```
  
- Run the application
  ```
  mvnw spring-boot:run
  ```
  
- Open the application from Internet broser with this URL:
  ```
  http://localhost:8080
  ```

## Environment Variables


| **Enviroment variables**     | **Description**                     | **Default value**                 |
|------------------------------|-------------------------------------|-----------------------------------|
| APP_PORT                     | Application port                    | 8080                              |
| SPRING_ACTIVE_PROFILES       | Active profiles                     | dev                               |
| MANAGEMENT_BASE_URL          | Management base URL                 | /management                       |
| DATASOURCE_DRIVER_CLASSNAME  | Driver classname of the datasource  | **[dev]** org.h2.Driver \         |
|                              |                                     | **[sit]** org.postgresql.Driver \ |
|                              |                                     | **[prod]** org.postgresql.Driver  | 
| DATASOURCE_URL               | URL of the datasource               |                                   |
| DATASOURCE_USERNAME          | Username of the datasource          |                                   |
| DATASOURCE_PASSWORD          | Password of the datasource          |                                   |
| JPA_DIALECT                  | JPA dialect                         |                                   |
| H2_CONSOLE_ENABLED           | H2 console flag                     |                                   |

## Original project

This is the foked project of https://github.com/wazooinc/spring-boot-3-todo-application with some improvements.