#FROM java:8
FROM openjdk:8-jre-alpine
EXPOSE 8091 8092
ADD /target/database-service-1.0.jar database-service-1.0.jar
ENTRYPOINT ["java","-jar","database-service-1.0.jar"]
