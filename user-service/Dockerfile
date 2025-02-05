# Use the official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Copy the entrypoint script into the container
COPY entrypoint.sh /app/

# Make the entrypoint script executable
RUN chmod +x /app/entrypoint.sh

# Expose the application port
EXPOSE 8081

# Set the entrypoint script as the container's entrypoint
ENTRYPOINT ["/app/entrypoint.sh"]
