
#docker pull --platform=linux/amd64 ubuntu:22.04

docker build -t puppeteer-app:1.0 -f ./Dockerfile .

#mkdir -p ./image && cd ./image
#docker run --rm -v $(pwd):/app/image -w /app puppeteer-app
#docker run --name puppeteer-app --hostname puppeteer -v $(pwd):/app/image -p 8000:8000 -w /app puppeteer-app:1.0 --network host


#cd /opt/git/repo/world-peace/docker/puppeteer/images
#docker run \
#  --name puppeteer-app \
#  --hostname puppeteer \
#  -v $(pwd):/app/images \
#  -p 8000:8000 \
#  -w /app puppeteer-app:2.0 \
#  --network wp_net

#curl -X POST http://localhost:8090/ai/listTools
#curl -X POST http://localhost:8090/ai/capture -d 'url=http://www.qq.com'
