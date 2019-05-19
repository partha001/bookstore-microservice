call ../changeDirectory.bat
call mvn clean install -f config-service\pom.xml
call docker build -f config-service\.docker\Dockerfile     --no-cache=true -t partha011/microservicenew/config-service:1.0     config-service\