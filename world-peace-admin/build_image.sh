### build the image and push to dockerhub
. ../docker/wp/.env
docker build -t ${WPADMIN_VERSION} -f ./Dockerfile .
docker tag ${WPADMIN_VERSION} ${REPO_URL}/${WPADMIN_VERSION}
docker push ${REPO_URL}/${WPADMIN_VERSION}
