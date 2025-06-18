from http.server import BaseHTTPRequestHandler, HTTPServer
import subprocess

class RequestHandler(BaseHTTPRequestHandler):
    def do_POST(self):
        # 获取数据长度
        content_length = int(self.headers['Content-Length'])
        # 读取POST数据
        post_data = self.rfile.read(content_length).decode('utf-8')

        # 解析URL参数（示例格式：url=https://example.com）
        params = dict(param.split('=') for param in post_data.split('&'))
        received_url = params.get('url', None)

        # 打印接收到的URL（可选）
        if received_url:
            print(f"Received URL: {received_url}")

        # call node.js script to capture the webpage
        try:
            result = subprocess.run(['node', 'capture_url.js', received_url], capture_output=True, text=True, check=True)
            output = result.stdout
            status_code = 200
        except subprocess.CalledProcessError as e:
            output = f"Error executing command: {e.stderr}"
            status_code = 500

        # 返回响应
        self.send_response(status_code)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()
        self.wfile.write(output.encode())

def run(server_class=HTTPServer, handler_class=RequestHandler, port=8000):
    server_address = ('0.0.0.0', port)  # 监听所有网卡，支持对外访问
    httpd = server_class(server_address, handler_class)
    print(f"Starting server on 0.0.0.0:{port} (external access enabled)...")
    httpd.serve_forever()

if __name__ == '__main__':
    run()