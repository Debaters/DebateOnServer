FROM openjdk:11
ARG JAR_FILE=/home/runner/work/DebateOnServer/DebateOnServer/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
