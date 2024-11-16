. ../env.sh $@

echo "Starting kafka cluster"
echo "log path: /opt/docker"
echo "using env: " && cat ./.env

docker-compose --compatibility -f ./docker-compose-mini.yml $args

#Test run kafka
#docker run -d --name kafka-server --hostname kafka-server \
#    --network app-tier \
#    -e KAFKA_CFG_NODE_ID=0 \
#    -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
#    -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
#    -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
#    -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka-server:9093 \
#    -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
#    bitnami/kafka:latest

#查看容器的实际运行状态
#docker ps
#docker logs kafka1 -f
#docker status <container_id>
#docker inspect <container_id>
#docker exec -it kafka1 /bin/bash
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh --create --topic test --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh --describe --topic test --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh --delete --topic test --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh --alter --topic test --partitions 3 --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-topics.sh --alter --topic test --config retention.ms=86400000  --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-console-consumer.sh --topic test --bootstrap-server localhost:9092
#docker exec -it kafka1 /opt/bitnami/kafka/bin/kafka-console-producer.sh --topic test --bootstrap-server localhost:9092