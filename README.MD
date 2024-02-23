#       Wish Peace All Over The World

### Project Description:
#### 1. world-peace-admin
    1) banner
    2) jasypt // 配置加密

#### 2. world-peace-api
    1) WebSocket Server
    2) Cashing // TODO

#### 3. world-peace-common
    1)
    2) util
    2.1) mybatis
    2.2) java Queue tools 

#### 4.world-peace-service
    1) mybatis
    https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/zh/index.html
    2) c3p0 connection pool
    3) dev,prd env

#### 5. test01
    1) evosuite // unit test
    2) email
    3) logback.xml
    4) tcp / udp

#### 6. world-peace-poc
    1) logback.xml
    2) kafka
        docker 安装kafka(未验证): https://doc.bulkall.top/kafka/install-docker-compose/
    3) WebSocket Client
    4) netty
    5) FastJson

#### 7. handnote

    ## build image using "docker build" based on dockerfile -> create container using "dock run or docker-compose"
    #1. 下载openjdk:11到本地
    docker search openjdk:11
    docker pull --platform=arm64 openjdk:11 #600+MB
    docker pull --platform=arm64 openjdk:11-jre-slim #production用jre即可,216MB
    
    #2.创建 Dockerfile和world-peace-api-1.0.jar
    [root@iZwz9g1xcfxo5neksecuy2Z admin]# ls
    Dockerfile  world-peace-api-1.0.jar
    [root@iZwz9g1xcfxo5neksecuy2Z admin]# cat Dockerfile
    FROM openjdk:11
    VOLUME /usr/app/project/
    ADD ./world-peace-api-1.0.jar /usr/app/project/world-peace-api.jar
    EXPOSE 8081
    ENTRYPOINT ["java","-jar","/usr/app/project/world-peace-api.jar"]
    MAINTAINER Jupiter
    
    #3.创建镜像
    cd /Users/jupiter/14.idea-workspace/world-peace/world-peace-api
    docker build -t world-peace-api:1.0 -f ./target/classes/Dockerfile .
    
    #4.查看镜像
    [root@iZwz9g1xcfxo5neksecuy2Z admin]# docker images
    REPOSITORY        TAG       IMAGE ID       CREATED              SIZE
    world-peace-api   1.0       3a99869c423b   About a minute ago   690MB  #以上创建的
    openjdk           11        47a932d998b7   19 months ago        654MB
    
    #5.运行容器
    docker run -d --network=wp_net --name wpapi --hostname wpapi -p 8081:8081 world-peace-api:1.0
    
    #6.查看运行容器状态
    [root@iZwz9g1xcfxo5neksecuy2Z admin]# netstat -anp | grep 8081
    tcp        0      0 0.0.0.0:8081            0.0.0.0:*               LISTEN      2305/docker-proxy
    [root@iZwz9g1xcfxo5neksecuy2Z admin]# docker ps
    CONTAINER ID   IMAGE                 COMMAND                  CREATED         STATUS         PORTS                               NAMES
    9303c7a3f356   world-peace-api:1.0   "java -jar /usr/apps…"   6 minutes ago   Up 4 seconds   0.0.0.0:8081->8081/tcp              wpapi
    [root@iZwz9g1xcfxo5neksecuy2Z ~]# curl http://localhost:8081/params/Jupiter/18
    [Jupiter, 18]
    jupiter@Jupiter-Mac ~$docker logs -f wpapi #查看app log
    [root@iZwz9g1xcfxo5neksecuy2Z ~]# docker exec -it wpapi /bin/bash
    
    #7.用docker-compose启动容器， -d 表示后台启动。需先执行步骤3构建镜像。
    cd ./docker
    docker-compose -f docker-compose.yml up -d
    [+] Running 2/3
    ⠦ Network wp_wp_net  Created                                                                                                    0.6s
    ✔ Container wpapi    Started                                                                                                    0.5s
    ✔ Container wppoc    Started                                                                                                    0.5s
    # 关闭容器，且删除容器
    docker-compose -f docker-compose.yml down --volumes
    [+] Running 3/2
    ✔ Container wpapi    Removed                                                                                                    0.2s
    ✔ Container wppoc    Removed                                                                                                    0.3s
    ✔ Network wp_wp_net  Removed                                                                                                    0.1s
    
    #8.容器需要在同一个network才能通讯，若创建容器时忘记指定network，可通过以下命令将容器加入network
    docker network connect --help #Usage:  docker network connect [OPTIONS] NETWORK CONTAINER
    docker network connect wp_default wppoc
    
    #***joined 容器(两个容器用同一个mac地址和ip)
    #docker run -d -it --name=container1 IMAGE #创建容器1
    #docker run -d -it --network=container:container1 --name=container2 IMAGE

