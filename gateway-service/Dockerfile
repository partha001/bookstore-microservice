#FROM java:8
FROM openjdk:8-jre-alpine
EXPOSE 8083
ADD /target/gateway-service-1.0.jar gateway-service-1.0.jar
ENTRYPOINT ["java","-jar","gateway-service-1.0.jar"]
