docker-compose -f ./docker-compose.yml up -d

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