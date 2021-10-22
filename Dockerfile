FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ADD application.properties .

ENTRYPOINT ["java","-jar", "-Dspring.config.location=application.properties", "/app.jar"]

EXPOSE 8080