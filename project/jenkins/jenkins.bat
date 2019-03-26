E:
cd  E:\Workspaces\Workspace_MicroserviceNew

call mvn clean install -f config-service\pom.xml
call mvn clean install -f discovery-service\pom.xml
call mvn clean install -f gateway-service\pom.xml
call mvn clean install -f product-service\pom.xml
call mvn clean install -f user-service\pom.xml



call docker build -f config-service\.docker\Dockerfile     --no-cache=true -t partha011/microservicenew/config-service:1.0     config-service\
call docker build -f discovery-service\.docker\Dockerfile  --no-cache=true -t partha011/microservicenew/discovery-service:1.0  discovery-service\
call docker build -f gateway-service\.docker\Dockerfile    --no-cache=true -t partha011/microservicenew/gateway-service:1.0    gateway-service\
call docker build -f product-service\.docker\Dockerfile    --no-cache=true -t partha011/microservicenew/product-service:1.0    product-service\
call docker build -f user-service\.docker\Dockerfile       --no-cache=true -t partha011/microservicenew/user-service:1.0       user-service\




