FROM openjdk:17
LABEL authors="Aurelien"
COPY target/housingService-*.jar housingService.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "housingService.jar"]