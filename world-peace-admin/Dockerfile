FROM openjdk:21-jdk-slim
ENV APPS_HOME=/apps
RUN mkdir -p $APPS_HOME
USER root
WORKDIR $APPS_HOME
VOLUME $APPS_HOME
ADD ./target/world-peace-admin-1.2.jar $APPS_HOME/world-peace-admin.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/apps/world-peace-admin.jar"]
MAINTAINER Jupiter

#docker run -d --name wpadmin --hostname wpadmin -p 8090:8090 --network=data_default -v /data/logs/wpadmin:/data/logs/wpadmin --restart=always world-peace-admin:1.0
#-v /path/on/host:/path/in/container
#docker exec -it wpadmin bash
#docker stop wpadmin
#docker rm wpadmin
#docker rmi world-peace-admin:1.0
#docker images
#docker ps
#docker logs wpadmin
#docker logs -f wpadmin
#docker image prune
#docker system prune
#docker system prune -a
#docker system prune -a --volumes
#docker system prune -a --volumes --force
#docker system prune -a --volumes --force --filter "label!=com.docker.compose.project=world-peace"
#docker system prune -a --volumes --force --filter "label!=com.docker.compose.project=world-peace" --filter "label!=com.docker.compose.service=wpadmin"
