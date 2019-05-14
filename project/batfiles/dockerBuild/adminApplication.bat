call ../changeDirectory.bat
call mvn clean install -f admin-application\pom.xml
call docker build -f admin-application\.docker\Dockerfile  --no-cache=true -t partha011/microservicenew/admin-application:1.0  admin-application\