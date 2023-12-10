FROM eclipse-temurin:17

WORKDIR /app
COPY target/training-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/training-0.0.1-SNAPSHOT.jar"]
