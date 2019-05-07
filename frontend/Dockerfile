FROM nginx:1.15

##installing vi and curl
#RUN apt-get update 
#RUN apt-get install -y vim
#RUN apt-get install -y curl

RUN rm /etc/nginx/conf.d/default.conf 
#COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From 'builder' stage copy over the artifacts in dist folder to default nginx public folder
COPY /dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]