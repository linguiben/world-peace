
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

cd /opt/git/repo/world-peace/docker/puppeteer-http/images
docker run \
  --rm \
  --name puppeteer-http \
  --hostname puppeteer \
  -v $(pwd):/app/images \
  -p 8000:8000 \
  -w /app \
  --network wp-net \
  --entrypoint python3 puppeteer-app:1.0 http_server.py

cd /opt/git/repo/world-peace/docker/puppeteer-stateless/images
docker run -it -d \
  --name puppeteer-statless2 \
  --hostname puppeteer \
  -v $(pwd):/app/images \
  -p 8001:8000 \
  -w /app \
  --network wp_net \
  --entrypoint python puppeteer-app:2.2 mcp_server_sse.py

#curl -X POST http://localhost:8090/ai/listTools
#curl -X POST http://localhost:8090/ai/capture -d 'url=http://www.qq.com'


# list tools
curl -i -X POST http://localhost:8080/mcp/ \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{"jsonrpc":"2.0","id":1,"method":"tools/list","params":{}}'

# call tool
curl -i -X POST http://localhost:8080/mcp/ \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{"jsonrpc":"2.0","id":2,"method":"tools/call","params":{"_meta":{"progressToken":2},"name":"system_info","arguments":{}}}'

# call tool
curl -i -X POST http://localhost:8080/mcp/ \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{"jsonrpc":"2.0","id":2,"method":"tools/call","params":{"_meta":{"progressToken":2},"name":"webpage_capture","arguments":{"url":"http://www.baidu.com"}}}'


curl -i -X POST http://jupiterSo.com:8001/mcp/ \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{"jsonrpc":"2.0","id":1,"method":"tools/list","params":{}}'

curl -i -X POST http://jupiterSo.com:8001/mcp/ \
  -H "Content-Type: application/json" \
  -H "Accept: application/json, text/event-stream" \
  -d '{"jsonrpc":"2.0","id":2,"method":"tools/call","params":{"name":"system_info","arguments":{"toolName":"system_info","\"\"":""}}}'

{"jsonrpc":"2.0","id":1,"method":"tools/call","params":{"name":"system_info","arguments":{"toolName":"system_info","url":"http://www.baidu.com"}}}