call ../changeDirectory.bat
call mvn clean install -f gateway-service\pom.xml
call docker build -f gateway-service\Dockerfile    --no-cache=true -t partha011/microservicenew/gateway-service:1.0    gateway-service\