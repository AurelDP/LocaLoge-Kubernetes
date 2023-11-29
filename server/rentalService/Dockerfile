FROM openjdk:17
LABEL authors="Nelson"
COPY target/rentalService-*.jar rentalService.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "rentalService.jar"]