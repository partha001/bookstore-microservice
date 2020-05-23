#FROM java:8
FROM openjdk:8-jre-alpine
EXPOSE 8085
ADD /target/product-service-1.0.jar product-service-1.0.jar
ENTRYPOINT ["java","-jar","product-service-1.0.jar"]
