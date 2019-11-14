# Image Nginx for Angular
FROM nginx:1.11-alpine
# Copy Project to container
COPY ./dist/micro-app-view/ /usr/share/nginx/html/
# Exdpose the port
EXPOSE 80
# Init project
CMD ["nginx","-g","daemon off;"]