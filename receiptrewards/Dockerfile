# Use the official OpenJDK base image with a specific version
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/receiptrewards-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot application will run on (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
