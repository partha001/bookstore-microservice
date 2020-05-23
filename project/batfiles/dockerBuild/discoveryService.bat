call ../changeDirectory.bat
call mvn clean install -f discovery-service\pom.xml
call docker build -f discovery-service\Dockerfile  --no-cache=true -t partha011/microservicenew/discovery-service:1.0  discovery-service\