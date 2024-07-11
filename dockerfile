FROM openjdk:11-jre-slim

EXPOSE 8082

RUN apt-get update && apt-get install -y maven

WORKDIR /app

ADD target/*.jar eventsProject.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/eventsProject.jar"]

