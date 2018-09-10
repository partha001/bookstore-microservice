FROM openjdk:8u181-jdk
RUN apt-get install wget
RUN wget https://repo1.maven.org/maven2/io/zipkin/java/zipkin-server/2.11.5/zipkin-server-2.11.5-exec.jar
EXPOSE 9411/tcp
ENTRYPOINT ["java","-jar","zipkin-server-2.11.5-exec.jar"]
#CMD ["java -jar /zipkin-server-2.8.3-exec.jar"]
MAINTAINER partha_biswas