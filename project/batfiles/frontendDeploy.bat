C:
cd  C:\Users\parbiswa\Documents\workspaces\MicroservicesNew
REM E:
REM cd E:\Workspaces\Workspace_MicroserviceNew


echo ********************** building the frontend **********************
cd frontend
call ng build --environment=dev
call docker build -f Dockerfile   --no-cache=true -t partha011/microservicenew/frontend:1.0  .

