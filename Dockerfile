FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

WORKDIR /app
ADD ./ /app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar motta.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/motta.jar"]
