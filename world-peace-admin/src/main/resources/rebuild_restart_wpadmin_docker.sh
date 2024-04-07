#!/bin/bash

### 1. build docker images
JENKINS_WORKSPACE=/root/.jenkins/workspace
TARGET_PATH=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/target
DOCKER_FILE=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/src/main/resources/Dockerfile
cd ${TARGET_PATH} && docker build -t world-peace-admin:1.0 -f ${DOCKER_FILE} .

### 2. delete the exiting and the running docker-container and then rerun
docker stop wpadmin
docker rm wpadmin
docker run -d --name wpadmin --hostname wpadmin -p 8090:8090 --network=data_default -v /data/logs/wpadmin:/data/logs/wpadmin -v /data/wpadmin:/usr/apps/wp/data --restart=always world-peace-admin:1.0
