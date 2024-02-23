FROM openjdk:11-jre-slim
ENV APPS_HOME=/usr/apps/wp
VOLUME $APPS_HOME
ADD ./target/world-peace-api-1.0.jar $APPS_HOME/world-peace-api.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/apps/wp/world-peace-api.jar"]
MAINTAINER Jupiter

#cd /Users/jupiter/14.idea-workspace/world-peace/world-peace-api
#docker build -t world-peace-api:1.0 -f ./target/classes/Dockerfile .
#docker run -d --name wpapi --hostname wpapi -p 8081:8081 world-peace-api:1.0