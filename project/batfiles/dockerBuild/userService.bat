call ../changeDirectory.bat
call mvn clean install -f user-service\pom.xml
call docker build -f user-service\.docker\Dockerfile       --no-cache=true -t partha011/microservicenew/user-service:1.0       user-service\