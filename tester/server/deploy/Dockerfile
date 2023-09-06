FROM openjdk:11.0.11-jre-slim as builder

# Copy the jar file into the container
COPY ./server.jar /server.jar

EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/server.jar"]
