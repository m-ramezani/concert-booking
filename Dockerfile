FROM maven:3-jdk-11 AS project-build
#FROM openjdk:11-jdk-slim-buster-apm-metric
RUN mkdir -p /app
WORKDIR /app
ADD src /app/src
ADD pom.xml /app
RUN mvn clean test install
# EXPOSE ${server.port}
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "./target/concert-booking-1.0.0.jar" ]