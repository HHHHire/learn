FROM adoptopenjdk/openjdk8:latest
WORKDIR /app
RUN mkdir log
COPY ./docker-compose.jar /app/docker-compose.jar
EXPOSE 7001
ENTRYPOINT ["nohup", "java", "-jar", "docker-compose.jar"]