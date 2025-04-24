# Kickstatz

Kickstatz is a Spring Boot application built with Java 21 and Maven. It leverages reactive programming and integrates with Redis, WebFlux, WebSocket, and PostgreSQL.

## Features

- Reactive Redis support
- WebFlux for building reactive RESTful services
- WebSocket for real-time communication
- PostgreSQL database integration

## Prerequisites

- Java 21
- Maven 3.8+
- PostgreSQL database
- Redis server

## Getting Started

### Build and Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd kickstatz
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Configuration

Update the `application.properties` or `application.yml` file to configure the database and Redis connection settings.

### Testing

Run the tests using:
```bash
mvn test
```

## Dependencies

- **Spring Boot Starter Data Redis**: For Redis integration
- **Spring Boot Starter Data Redis Reactive**: For reactive Redis support
- **Spring Boot Starter WebFlux**: For reactive RESTful services
- **Spring Boot Starter WebSocket**: For WebSocket communication
- **PostgreSQL Driver**: For database connectivity
- **Lombok**: For reducing boilerplate code
- **Spring Boot Starter Test**: For testing

## Reference Documentation

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data Redis](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.nosql.redis)
- [Spring WebFlux](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web.reactive)
- [Spring WebSocket](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#websocket)
- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.5/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.4.5/maven-plugin/build-image.html)
- [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/3.4.5/reference/data/nosql.html#data.nosql.redis)
- [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/3.4.5/reference/data/nosql.html#data.nosql.redis)
- [Spring Reactive Web](https://docs.spring.io/spring-boot/3.4.5/reference/web/reactive.html)
- [WebSocket](https://docs.spring.io/spring-boot/3.4.5/reference/messaging/websockets.html)

## Guides

The following guides illustrate how to use some features concretely:

- [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
- [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
- [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)

## Maven Parent Overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM. While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent. To prevent this, the project POM contains empty overrides for these elements. If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## License

This project is licensed under the [MIT License](LICENSE).

## Author

- **Kutlu** - [GitHub Profile](https://github.com/mkutlu)
```