FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /home/app
COPY src src
COPY pom.xml .
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /home/app/target/demo-application.jar /app/demo-application.jar
ENTRYPOINT ["java", "-jar", "/app/demo-application.jar"]


