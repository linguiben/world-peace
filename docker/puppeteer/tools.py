import subprocess

def webpage_capture(url) -> str:
    """capture the webpage and return the screen short path saved in the host

    Returns:
        str: the screen short path saved in the host
    """

    received_url = url

    # 打印接收到的URL（可选）
    if received_url:
        print(f"Received URL: {received_url}")
        
    # call node.js script to capture the webpage
    try:
        result = subprocess.run(
            ['node', 'capture_url.js', received_url],
            capture_output=True,
            text=True,
            check=True
        )
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        return f"Error capturing webpage: {e.stderr.strip()}"

if __name__ == '__main__':
    print(webpage_capture("https://www.baidu.com"))

# This code can be used to get the host information in a structured format.
# It can be integrated into a larger application or used standalone.
# The output will be a JSON string containing the system information.
