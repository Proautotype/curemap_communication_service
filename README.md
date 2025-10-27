# CureMap Communication Service

A Spring Boot-based microservice for handling real-time communication features including posts and comments, with WebSocket support for real-time updates.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Development](#development)
- [Testing](#testing)
- [Deployment](#deployment)
- [Monitoring](#monitoring)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Post Management**: Create and manage posts
- **Real-time Updates**: WebSocket integration for live updates
- **RESTful API**: Standardized API endpoints following REST principles
- **JPA Auditing**: Automatic tracking of creation and modification timestamps
- **API Documentation**: Interactive API documentation with Swagger/OpenAPI
- **Containerization**: Docker support for easy deployment

## Tech Stack

- **Framework**: Spring Boot 3.5.6
- **Language**: Java 21
- **Database**: PostgreSQL (via JPA)
- **Caching**: Redis
- **API Documentation**: OpenAPI 3.0
- **Build Tool**: Maven
- **Containerization**: Docker
- **Real-time**: Socket.IO

## Prerequisites

- Java 21 JDK or later
- Maven 3.6.0 or later
- Docker (optional, for containerized deployment)
- PostgreSQL 12+ (or compatible database)
- Redis (for caching and real-time features)

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd curemap-communication-service
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Configuration

Configuration can be managed through `application.yml` or environment variables. Key configurations include:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/communication_db
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

redis:
  host: localhost
  port: 6379

app:
  socket:
    port: 9092
```

## API Documentation

Once the application is running, you can access the API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Development

### Project Structure

```
src/main/java/com/custard/curemapcommunicationservice/
├── adapter/                # Adapters (REST controllers, repositories, etc.)
│   ├── rest/               # REST controllers
│   ├── persistence/        # JPA entities and repositories
│   └── websocket/          # WebSocket configuration and handlers
├── application/            # Application layer (use cases, commands, etc.)
│   ├── commands/           # Command objects
│   └── usecases/           # Application use cases
├── domain/                 # Domain model and business logic
│   ├── model/              # Domain entities
│   └── service/            # Domain services
└── infrastructure/         # Infrastructure concerns
    ├── config/             # Configuration classes
    ├── exception/          # Exception handling
    └── security/           # Security configuration
```

### Code Style

This project follows the Google Java Style Guide with the following exceptions:
- 4-space indentation
- Line length of 120 characters

## Testing

Run tests using:
```bash
mvn test
```

## Deployment

### Docker

1. Build the Docker image:
   ```bash
   docker build -t curemap/communication-service .
   ```

2. Run the container:
   ```bash
   docker-compose up -d
   ```

## Monitoring

The service includes Spring Boot Actuator endpoints for monitoring:
- Health: `http://localhost:8080/actuator/health`
- Info: `http://localhost:8080/actuator/info`
- Metrics: `http://localhost:8080/actuator/metrics`

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

[Your License Here]

---

**Contact**: Winston - [winstyngyen@gmail.com](mailto:winstyngyen@gmail.com)
