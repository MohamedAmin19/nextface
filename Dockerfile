# Stage 1: Build the Spring Boot application
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set working directory inside the container
WORKDIR /app

# Copy the pom.xml and download the dependencies (for faster builds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your application code and build it
COPY src /app/src
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot application
FROM openjdk:17-jre-slim

# Set working directory inside the container
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/target/nextface-0.0.1-SNAPSHOT.jar app.jar

# Expose the default port of Spring Boot
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
