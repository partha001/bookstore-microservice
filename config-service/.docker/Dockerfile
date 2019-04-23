#FROM java:8
FROM openjdk:8-jre-alpine
EXPOSE 8082
ADD /target/config-service-1.0.jar config-service-1.0.jar
ENTRYPOINT ["java","-jar","config-service-1.0.jar"]

