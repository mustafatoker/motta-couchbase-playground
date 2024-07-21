# Motta Application

This project contains a Spring Boot application that connects to a Couchbase server. The project is dockerized and can
be built and deployed using Docker and Docker Compose.

## Prerequisites

- Docker
- Docker Compose

## Directory Structure

```
motta/
├── .docker/
│ └── couchbase/
│ └── Dockerfile
├── src/
│ └── main/
│ └── java/
│ └── resources/
│ └── application.yml
├── target/
│ └── motta.jar (generated after build)
├── Dockerfile
├── docker-compose.yml
└── README.md
```

## Run the Application with Docker Compose

### 1. Clone the Repository

Clone the repository to your local machine.

```sh
git clone https://github.com/mustafatoker/motta.git
cd motta
```

2. Run Docker Compose
   Use Docker Compose to start both Couchbase and the Spring Boot application containers.

```
docker-compose up -d
```

This command will start both Couchbase and the Spring Boot application, ensuring they can communicate on the same Docker
network.

Accessing the Application
Once the application is up and running, you can access it at:

```
http://localhost:8080
```

Swagger UI can be accessed (if configured) at:

```
http://localhost:8080/api/swagger-ui.html
```

### Troubleshooting

- Use docker-compose logs to view logs and troubleshoot any issues during startup.
- Ensure Couchbase and the Spring Boot application are properly configured and running on the same Docker network.
- Ensure the application is running on port 8080 and Couchbase is running on port 8091.

### TODO
- Add unit tests
- Add integration tests
- Integrate with CI/CD pipeline
- Add more features to the application
- Add monitoring and logging
- Convert the application to spring boot reactive

