
#docker pull --platform=linux/amd64 ubuntu:22.04

docker build -t -f ./Dockerfile puppeteer-app:1.0 .

#mkdir -p ./image && cd ./image
#docker run --rm -v $(pwd):/app/image -w /app puppeteer-app
