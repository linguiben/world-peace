FROM openjdk:11-jre-slim
ENV APPS_HOME=/usr/apps/wp
VOLUME $APPS_HOME
ADD ./world-peace-admin-1.0.jar $APPS_HOME/world-peace-admin.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/apps/wp/world-peace-admin.jar"]
MAINTAINER Jupiter

#cd /Users/jupiter/14.idea-workspace/world-peace/world-peace-admin
#docker build -t world-peace-admin:1.0 -f ./Dockerfile .
#docker run -d --name wpadmin --hostname wpadmin -p 8090:8090 --network=data_default -v /data/logs/wpadmin:/data/logs/wpadmin --restart=always world-peace-admin:1.0
#-v /path/on/host:/path/in/container
