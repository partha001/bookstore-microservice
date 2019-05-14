call changeDirectory.bat

cd frontend
call ng build --environment=dev
call docker build -f Dockerfile   --no-cache=true -t partha011/microservicenew/frontend:1.0  .

