```shell

docker pull registry.cn-shenzhen.aliyuncs.com/jupiterso/postgres:12.20

docker search postgres
docker pull postgres
docker images
docker run -p 25432:5432 --name=postgres -e "POSTGRES_HOST_AUTH_METHOD=trust" 340f3238aa46
docker start postgres

docker network ls #查看network
docker network create wp-network #创建 network
docker tag registry-vpc.cn-shenzhen.aliyuncs.com/jupiterso/postgres:12.20 postgres:latest
docker run -d --user appuser -p 25432:5432 --name postgres --network default -e POSTGRES_PASSWORD=<password> registry.openanolis.cn/openanolis/postgres:10.21-8.6 
docker run --user appuser -d -p 25432:5432 --name postgres --network default -e POSTGRES_PASSWORD=<password> postgres:latest
```

#自动拉取龙蜥社区镜像并启动容器
https://cr.console.aliyun.com/cn-shenzhen/instances/artifact 
