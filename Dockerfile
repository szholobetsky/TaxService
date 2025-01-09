FROM openjdk:17-slim-buster
LABEL authors="stzh"
WORKDIR /app

COPY build/libs/TaxService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]