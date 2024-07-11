FROM openjdk:11-jre-slim

EXPOSE 8082

RUN apt-get update && apt-get install -y maven

ADD target/*.jar eventsProject.jar

ENTRYPOINT [ "java", "-jar", "/eventsProject.jar" ]

