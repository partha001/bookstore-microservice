#FROM java:8
FROM openjdk:8-jre-alpine
EXPOSE 8081
ADD /target/discovery-service-1.0.jar discovery-service-1.0.jar
ENTRYPOINT ["java","-jar","discovery-service-1.0.jar"]
