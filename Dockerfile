FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY target/warfare-simulation-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
