# Image Java
FROM openjdk:8-jdk-alpine
# Copy Project to container
COPY ./build/libs/micro-app-service-0.0.1-SNAPSHOT.jar /usr/local
# Folder as workdir
WORKDIR /usr/local
# Execute project
ENTRYPOINT java -jar micro-app-service-0.0.1-SNAPSHOT.jar