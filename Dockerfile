FROM eclipse-temurin:11
WORKDIR /usr/src/app
COPY . .
RUN ./mvnw package -DskipTests
EXPOSE 8080
ENTRYPOINT java -jar target/*.jar
