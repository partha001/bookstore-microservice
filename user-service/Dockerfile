#FROM java:8
FROM openjdk:8-jre-alpine
EXPOSE 8086
ADD /target/user-service-1.0.jar user-service-1.0.jar
ENTRYPOINT ["java","-jar","user-service-1.0.jar"]
