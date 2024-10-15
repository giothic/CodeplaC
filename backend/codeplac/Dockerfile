FROM ubuntu:latest AS build

ARG DATABASE_USERNAME=mockUser
ARG DATABASE_PASSWORD=mockPassword
ARG DATABASE_URL=jdbc:mysql://mockUrl:3306/mockDb

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -Dspring.datasource.username=$DATABASE_USERNAME -Dspring.datasource.password=$DATABASE_PASSWORD -Dspring.datasource.url=$DATABASE_URL

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build target/codeplac-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]