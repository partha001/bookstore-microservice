FROM openjdk:8u181-jdk
#COPY zipkin-server-2.8.3-exec.jar /zipkin-server-2.8.3-exec.jar
RUN wget https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec
EXPOSE 9411/tcp
#ENTRYPOINT ["java","-jar","zipkin-server-2.8.3-exec.jar"]
#CMD ["java -jar /zipkin-server-2.8.3-exec.jar"]
MAINTAINER partha_biswas