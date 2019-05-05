#FROM nginx:1.13.3-alpine
FROM nginx
RUN apt-get update 
RUN apt-get install -y vim
RUN apt-get install -y curl
## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*
## From 'builder' stage copy over the artifacts in dist folder to default nginx public folder
COPY /dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
