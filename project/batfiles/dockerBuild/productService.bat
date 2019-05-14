call ../changeDirectory.bat
call mvn clean install -f product-service\pom.xml
call docker build -f product-service\.docker\Dockerfile    --no-cache=true -t partha011/microservicenew/product-service:1.0    product-service\