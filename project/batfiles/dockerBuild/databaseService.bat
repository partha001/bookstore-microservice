call ../changeDirectory.bat
call mvn clean install -f database-service\pom.xml
call docker build -f database-service\.docker\Dockerfile   --no-cache=true -t partha011/microservicenew/database-service:1.0   database-service\