REM C:
REM cd  C:\Users\parbiswa\Documents\workspaces\MicroservicesNew
E:
cd E:\Workspaces\Workspace_MicroserviceNew


call mvn clean install -f database-service\pom.xml
start java -jar database-service\target\database-service-1.0.jar


call mvn clean install -f config-service\pom.xml
start java -jar config-service\target\config-service-1.0.jar

call mvn clean install -f discovery-service\pom.xml
start java -jar discovery-service\target\discovery-service-1.0.jar

call mvn clean install -f gateway-service\pom.xml
call mvn clean install -f admin-application\pom.xml
call mvn clean install -f product-service\pom.xml
call mvn clean install -f user-service\pom.xml


echo ********************** starting the microservices **********************
start java -jar admin-application\target\admin-application-1.0.jar
start java -jar gateway-service\target\gateway-service-1.0.jar
start java -jar product-service\target\product-service-1.0.jar
start java -jar user-service\target\user-service-1.0.jar


echo ********************** starting to build docker images **********************
call docker build -f database-service\.docker\Dockerfile   --no-cache=true -t partha011/microservicenew/database-service:1.0   database-service\
call docker build -f admin-application\.docker\Dockerfile  --no-cache=true -t partha011/microservicenew/admin-application:1.0  admin-application\
call docker build -f config-service\.docker\Dockerfile     --no-cache=true -t partha011/microservicenew/config-service:1.0     config-service\
call docker build -f discovery-service\.docker\Dockerfile  --no-cache=true -t partha011/microservicenew/discovery-service:1.0  discovery-service\
call docker build -f gateway-service\.docker\Dockerfile    --no-cache=true -t partha011/microservicenew/gateway-service:1.0    gateway-service\
call docker build -f product-service\.docker\Dockerfile    --no-cache=true -t partha011/microservicenew/product-service:1.0    product-service\
call docker build -f user-service\.docker\Dockerfile       --no-cache=true -t partha011/microservicenew/user-service:1.0       user-service\

echo ********************** building the frontend **********************
cd frontend
ng build --environment=dev
docker build -f Dockerfile   --no-cache=true -t partha011/microservicenew/frontend:1.0  .

