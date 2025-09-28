# Stage 1: Build the Spring Boot application
FROM maven:3.8.4-openjdk-17-slim as builder

WORKDIR /app

# Copy the Maven files and build dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source files and build the app
COPY src /app/src
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot application
FROM openjdk:17-jre-slim

WORKDIR /app

# Copy the built jar file from the previous stage
COPY --from=builder /app/target/nextface-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
