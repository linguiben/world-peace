cd "$(dirname "$0")"
. ../env.sh $@

docker-compose -f ./docker-compose.yml --compatibility up -d
