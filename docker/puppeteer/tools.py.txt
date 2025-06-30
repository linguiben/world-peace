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

def run_cmd(command: str) -> str:
    """run a Linux command in the host.
    such as, run a command like `ls -l /home/user` to list the files in the directory "/home/user".
    The command can be any valid Linux command.
    or run a command like `cd /home/user && ls -l` to change directory to "/home/user" and list the files in the directory.
    or run a command like `curl https://www.example.com` to fetch the content of a webpage.
    or run a command like `python -c "print(1+1)"` to run a Python script, it will return "2"
    or run a python command like `python -c "print('strawberry'.count('r'))"` to count the number of occurrences of the letter 'r' in the string "strawberry", it will return "3".

    Args:
        command (str): the command to run

    Returns:
        str: the output of the command
    """
    print(f"Running command: {command}")
    # command_list = command.split() if isinstance(command, str) else command
    # if not isinstance(command, list):
    #     return "Error: command must be a string or a list of strings."
    try:
        result = subprocess.run(
            command,
            shell=True,
            capture_output=True,
            text=True,
            check=True
        )
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        return f"Error: {e.stderr.strip()}"

if __name__ == '__main__':
    print(webpage_capture("https://www.baidu.com"))

# This code can be used to get the host information in a structured format.
# It can be integrated into a larger application or used standalone.
# The output will be a JSON string containing the system information.
