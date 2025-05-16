FROM openjdk:17-jdk-slim
VOLUME /tmp
# Copy the built JAR file from the previous stage to the container
COPY target/search-index-0.0.1-SNAPSHOT.jar search-index.jar
EXPOSE 8080
# Set the command to run the application
ENTRYPOINT ["java", "-jar", "search-index.jar"]
