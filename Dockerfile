FROM maven:3.6-openjdk-17 AS build
WORKDIR /builder
COPY . /builder/
RUN mvn clean package

FROM openjdk:17.0.1-jdk
WORKDIR /app
COPY --from=build /builder/target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
