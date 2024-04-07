## Re-build wpadmin docker container
### 1. build docker images
```shell
# upload to aliyun ECS
#scp /Users/jupiter/14.idea-workspace/world-peace/world-peace-admin/target/world-peace-admin-1.0.jar root@aliyun:/opt/apps
JENKINS_WORKSPACE=/root/.jenkins/workspace
TARGET_PATH=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/target
DOCKER_FILE=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/src/main/resources/Dockerfile
cd ${TARGET_PATH} && docker build -t world-peace-admin:1.0 -f ${DOCKER_FILE} .
```
### 2. delete the exiting and the running docker-container and then rerun
```shell
docker stop wpadmin
docker rm wpadmin
docker run -d --name wpadmin --hostname wpadmin -p 8090:8090 --network=data_default -v /data/logs/wpadmin:/data/logs/wpadmin -v /data/wpadmin:/usr/apps/wp/data --restart=always world-peace-admin:1.0
```

### For local test
```shell
#cd /Users/jupiter/14.idea-workspace/world-peace/world-peace-admin  
#docker build -t world-peace-admin:1.0 -f ./Dockerfile .  
#docker run -d --name wpadmin --hostname wpadmin -p 8090:8090 --network=data_default -v /data/logs/wpadmin:/data/logs/wpadmin --restart=always world-peace-admin:1.0  
#-v /path/on/host:/path/in/container  
````

### run nginx1
```shell
-v /data/ssl:/data/ssl --restart=always registry.cn-hangzhou.aliyuncs.com/cloudm/nginx:v1  
docker run -d --name nginx1 --hostname nginx -p 80:80 -p 443:443 --network=data_default -v /static/resource:/static/resource:rw -v /data/logs/nginx:/var/log/nginx -v /static/formdata/pro:/static/formdata/pro  
data/logs/wpadmin
```

### test script
```shell
#/bin/bash
read  -p  "请输入<container_name:port>\\n e.g.: wpadmin:8089 " container_name
docker exec -it nginx /bin/bash -c "sed -i 's/java-master:8082/$container_name/g' /etc/nginx/vhost/template.conf && kill -HUP 1"
echo "ok."

docker exec -it nginx /bin/bash -c "sed -i 's/java-master:8082/wpadmin:8090/g' /etc/nginx/vhost/template.conf && kill -HUP 1"

#nginx
#主配置文件通常位于/etc/nginx/nginx.conf或/usr/local/nginx/conf/nginx.conf
#其中，include /etc/nginx/nginx.conf
```

```shell
刷新nginx配置
root@nginx:/etc/nginx/vhost# nginx -t #测试ngix配置文件
nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
nginx: configuration file /etc/nginx/nginx.conf test is successful
root@nginx:/etc/nginx/vhost#
root@nginx:/etc/nginx/vhost# nginx -s reload #刷新配置文件
sudo service nginx restart
```

