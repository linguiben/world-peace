docker-compose -f ./docker-compose.yml --compatibility up -d
echo "log path: /opt/docker"
cat ./.env
docker ps -a | grep redis


