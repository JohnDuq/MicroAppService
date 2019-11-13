# Image Mysql
FROM mysql:latest
# Add Password ROOT
ENV MYSQL_ROOT_PASSWORD=123456
# Copy file to mysql container where going to execute for default
COPY ./init.sql /docker-entrypoint-initdb.d/