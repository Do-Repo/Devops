FROM openjdk:11-jre-slim

EXPOSE 8082

ADD target/*.jar eventsProject.jar

ENTRYPOINT [ "java -Dspring.profiles.active=dockerembbed", "oauth-security -jar", "/eventsProject.jar" ]

