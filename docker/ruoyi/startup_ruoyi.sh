
# copy ./* to /opt/docker/nginx if file not exists
#if [ ! -f /opt/docker/nginx/nginx.conf ]; then
#  mkdir -p /opt/docker/nginx
#  cp -r ./* /opt/docker/nginx
#fi

docker-compose -f ./docker-compose-ruoyi.yml --compatibility up -d
echo "log path: /opt/docker"
cat ./.env