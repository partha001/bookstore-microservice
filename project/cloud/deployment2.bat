call kubectl delete namespace bookstorens
call kubectl create -f deployment2-ns.yaml
call kubectl create -f deployment2-application.yaml --namespace=bookstorens