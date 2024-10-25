FROM maven:3.6-openjdk-17 AS build
WORKDIR /builder
COPY . /builder/
RUN mvn clean package

FROM openjdk:17.0.1-jdk
WORKDIR /app
COPY --from=build /builder/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:mysql://${ENDPOINT_MYSQL}:3306/myStock", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
