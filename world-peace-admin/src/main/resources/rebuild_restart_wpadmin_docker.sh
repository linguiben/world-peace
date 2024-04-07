# upload to aliyun ECS
#scp /Users/jupiter/14.idea-workspace/world-peace/world-peace-admin/target/world-peace-admin-1.0.jar root@aliyun:/opt/apps
JENKINS_WORKSPACE=/root/.jenkins/workspace
TARGET_PATH=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/target
DOCKER_FILE=${JENKINS_WORKSPACE}/maven_world_peace/world-peace-admin/src/main/resources/Dockerfile
cd ${TARGET_PATH} && docker build -t world-peace-admin:1.0 -f ${DOCKER_FILE} .
