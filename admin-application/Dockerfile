FROM java:8
#FROM openjdk:8-jre-alpine
EXPOSE 8088
ADD /target/admin-application-1.0.jar admin-application-1.0.jar
ENTRYPOINT ["java","-jar","admin-application-1.0.jar"]
