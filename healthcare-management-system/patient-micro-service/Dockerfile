# Dockerfile
# Use OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/*.jar app.jar

# Expose port (change according to your service)
EXPOSE 8181

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
