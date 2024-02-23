FROM openjdk:11-jre-slim
ENV APPS_HOME=/usr/apps/wp
VOLUME $APPS_HOME
ADD ./target/world-peace-poc-1.0.jar $APPS_HOME/world-peace-poc.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/apps/wp/world-peace-poc.jar"]
MAINTAINER Jupiter

#cd /Users/jupiter/14.idea-workspace/world-peace/world-peace-poc
#docker build -t world-peace-poc:1.0 -f ./target/classes/Dockerfile .
#docker run -d --name wppoc --hostname wppoc -p 18080:8080 world-peace-poc:1.0