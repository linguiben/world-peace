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

# run nginx1
docker run -d --name nginx1 --hostname nginx -p 80:80 -p 443:443 --network=data_default -v
/static/resource:/static/resource:rw -v /data/logs/nginx:/var/log/nginx -v /static/formdata/pro:/static/formdata/pro
-v /data/ssl:/data/ssl --restart=always registry.cn-hangzhou.aliyuncs.com/cloudm/nginx:v1

# upload to aliyun ECS
scp /Users/jupiter/14.idea-workspace/world-peace/world-peace-admin/target/world-peace-admin-1.0.jar root@aliyun:/opt/apps
# build wpadmin
docker stop wpadmin
docker rm wpadmin
cd ~/apps && docker build -t world-peace-admin:1.0 -f ./Dockerfile .
docker run -d --name wpadmin --hostname wpadmin -p 8090:8090 --network=data_default -v /data/logs/wpadmin:/data/logs/wpadmin -v /data/wpadmin:/usr/apps/wp/data --restart=always world-peace-admin:1.0


data/logs/wpadmin

#/bin/bash
read  -p  "请输入<container_name:port>\\n e.g.: wpadmin:8089 " container_name
docker exec -it nginx /bin/bash -c "sed -i 's/java-master:8082/$container_name/g' /etc/nginx/vhost/template.conf && kill -HUP 1"
echo "ok."

docker exec -it nginx /bin/bash -c "sed -i 's/java-master:8082/wpadmin:8090/g' /etc/nginx/vhost/template.conf && kill -HUP 1"

root@nginx:/etc/nginx/vhost# nginx -t
nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
nginx: configuration file /etc/nginx/nginx.conf test is successful
root@nginx:/etc/nginx/vhost#
root@nginx:/etc/nginx/vhost# nginx -s reload
