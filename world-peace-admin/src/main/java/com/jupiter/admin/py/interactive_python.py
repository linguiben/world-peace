# interactive_python.py
import sys

def add(a, b):
    return a + b

def greet(name):
    return f"Hello, {name}!"

def run_interactive_shell():
    while True:
        try:
            line = sys.stdin.readline()
            if not line or line.strip().lower() == 'exit':
                print("收到退出指令，正在退出...")
                sys.stdout.flush()  # Ensure output is flushed
                break

            try:
                result = eval(line.strip())  # 支持表达式
                print(result)  # 输出到 stdout
                print("\nwaiting for command:")
                sys.stdout.flush()  # Ensure output is flushed
            except Exception as e:
                print(f"Error: {e}")
                sys.stdout.flush()  # Ensure output is flushed

        except KeyboardInterrupt:
            print("\n退出程序...")
            sys.stdout.flush()  # Ensure output is flushed
            break

if __name__ == '__main__':
    print("欢迎使用交互式 Python Shell！输入 'exit' 退出。")
    sys.stdout.flush()  # Ensure output is flushed
    run_interactive_shell()