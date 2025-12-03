# Use Java 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the built JAR file
ARG JAR_FILE=target/smartcalcpro-backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose port (Render will set PORT automatically)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
