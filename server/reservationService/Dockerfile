FROM openjdk:17
LABEL authors="Nelson"
COPY target/reservationService-*.jar reservationService.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "reservationService.jar"]