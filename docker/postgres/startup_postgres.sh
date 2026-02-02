docker-compose -f ./docker-compose.yml --compatibility up -d
echo "log path: /opt/docker/postgres"
docker ps -a | grep postgres
