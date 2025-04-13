## Re-build wpadmin docker container
### 1. build docker images
```shell
# upload to aliyun ECS
#scp /Users/jupiter/14.idea-workspace/world-peace/world-peace-admin/target/world-peace-admin-1.0.jar root@aliyun:/opt/apps
git reset --hard && git pull origin master
JENKINS_WORKSPACE=/root/.jenkins/workspace
TARGET_PATH=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/target
DOCKER_FILE=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/src/main/resources/Dockerfile
cd ${TARGET_PATH} && docker build -t world-peace-admin:1.0 -f ${DOCKER_FILE} .

fyi: 
# User specific environment and startup programs
export JAVA_HOME=/opt/jdk/jdk-21.0.4
export MAVEN_HOME=/opt/mvn/apache-maven-3.9.9
export MVN_HOME=$MAVEN_HOME
export PATH=$JAVA_HOME/bin:$MVN_HOME/bin:$PATH
export GIT_REPO=/opt/git/repo
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

### git proxies 
```shell
# 1.配置git的代理之前先检查一下: 当前是否已存在git代理
git config --global --get http.proxy && git config --global --get https.proxy
```
```shell
# 2.配置curl, brew等使用环境变量的代理配置，只配置成当前terminal生效
export http_proxy='http://localhost:8086' && export https_proxy=${http_proxy} #若需配置git代理，运行第3步骤
# 3.配置git的代理，此配置后所有用户均生效，若要取消代理需要运行第4步
git config --global http.proxy ${http_proxy} && git config --global https.proxy ${http_proxy}
```
```shell
# 4.1 测试能否连接到github(使用代理，或使用环境默认的代理)
curl 'https://github.com/Homebrew/brew.git/' #测试自动使用环境配置的代理
```
```shell
# 4.2 测试能否连接到github(手动指定代理)
curl -x ${http_proxy} 'https://github.com/Homebrew/brew.git/' #测试手动指定代理
```
```shell
# 6.取消代理(unset)
git config --global --unset http.proxy && git config --global --unset https.proxy
```
