# Use OpenJDK base image for building the app
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application (skip tests for faster builds)
RUN ./mvnw clean package -DskipTests

# Use a smaller JRE base image to run the app
FROM openjdk:17-jre-slim

# Set the working directory in the second stage (for the final image)
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/target/nextface-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on (default Spring Boot is 8080)
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
