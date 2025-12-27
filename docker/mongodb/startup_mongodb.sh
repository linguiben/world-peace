
mkdir -p /opt/docker/mongodb
# docker run -d \
#   --name mongodb \
#   -p 27017:27017 \
#   -v ~/mongo-data:/opt/docker/mongodb/data \
#   -e MONGO_INITDB_ROOT_USERNAME=root \
#   -e MONGO_INITDB_ROOT_PASSWORD=123456 \
#   mongo:latest
### run at: mongodb://root:123456@localhost:27017
### test access:
### docker exec -it mongodb mongosh -u root -p 123456
### show dbs

docker-compose -f ./docker-compose.yml --compatibility up -d
echo "data path: /opt/docker/mongodb"
if [ -f ./.env ]; then
  cat ./.env
fi
docker ps -a | grep mongodb

