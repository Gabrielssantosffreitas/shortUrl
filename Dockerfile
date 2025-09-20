FROM maven:3.9.6-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install

FROM openjdk:21
COPY --from=build app/target/EncurtadorDelinkSimples-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
