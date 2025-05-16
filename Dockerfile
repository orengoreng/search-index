# Use an official Maven image as the base image
FROM maven:3.8.5-openjdk-17-slim AS builder
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package
# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim
RUN mkdir /app
# Copy the built JAR file from the previous stage to the container
COPY --from=builder /app/target/search-index-0.0.1-SNAPSHOT.jar search-index.jar
EXPOSE 8080
# Set the command to run the application
ENTRYPOINT ["java", "-jar", "search-index.jar"]
