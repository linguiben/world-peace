. ../env.sh $@

PERSIST_PATH=/Users/jupiter/99.data/6.docker/zoo #update this for the path

PERSIST_PATH=$PERSIST_PATH docker-compose --compatibility -f ./docker-compose.yml $args
