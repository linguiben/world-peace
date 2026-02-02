export JAVA_HOME=$JAVA21_HOME
mvn clean install -f ./pom.xml
# scp ./target/world-peace-admin*.jar root@JupiterSo.com:/opt/docker/wpadmin/world-peace-admin.jar
