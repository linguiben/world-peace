#!/usr/bin/env bash
# This is used to start a Xvfb, Openbox, X11VNC, and a browser on the remote server,
# so that we can connect to it via VNC and use the browser to login Twitter.
# to install the dependencies, please run:
# sudo dnf install -y openbox x11vnc
set -e

export DISPLAY=:99

# 1. 启动虚拟显示
if ! pgrep -f "Xvfb :99" >/dev/null; then
  Xvfb :99 -screen 0 1440x900x24 -nolisten tcp >/tmp/xvfb.log 2>&1 &
  sleep 2
fi

# 2. 启动窗口管理器
if ! pgrep -x openbox >/dev/null; then
  openbox >/tmp/openbox.log 2>&1 &
  sleep 1
fi

# 3. 启动 VNC
if ! pgrep -f "x11vnc.*:99" >/dev/null; then
  x11vnc -display :99 -forever -shared -rfbport 5900 >/tmp/x11vnc.log 2>&1 &
  sleep 1
fi

# 4. 启动浏览器
if command -v firefox >/dev/null 2>&1; then
  firefox https://x.com/i/flow/login >/tmp/firefox.log 2>&1 &
elif command -v chromium-browser >/dev/null 2>&1; then
  chromium-browser --enable-plugins \
  --enable-extensions \
  --enable-user-scripts \
  --enable-printing --enable-sync \
  --enable-unsafe-swiftshader \
  --auto-ssl-client-auth \
  --remote-debugging-port=18800 \
  --user-data-dir=/root/.openclaw/browser/openclaw/user-data \
  --no-first-run \
  --no-default-browser-check \
  --incognito \
  --no-sandbox \
  --disable-dev-shm-usage --disable-gpu --disable-software-rasterizer \
  https://x.com/i/flow/login >/tmp/chromium-browser.log 2>&1 &
else
  echo "No browser found."
fi

# 5. 检查是否所有进程都启动成功
for i in {1..5}; do
  if ! pgrep -f "Xvfb :99" >/dev/null; then
    echo "Xvfb started failed, please check the log: /tmp/xvfb.log"
    exit 1
  fi
  if ! pgrep -x openbox >/dev/null; then
    echo "Openbox started failed, please check the log: /tmp/openbox.log"
    exit 1
  fi
  if ! pgrep -f "x11vnc.*:99" >/dev/null; then
    echo "x11vnc started failed, please check the log: /tmp/x11vnc.log"
    exit 1
  fi
  if ! pgrep -f "firefox|chromium-browser" >/dev/null; then
    echo "Browser started failed, please check the log: /tmp/firefox.log or /tmp/chromium-browser.log"
    exit 1
  fi
  sleep 1
done

echo "All processes started successfully."

echo "Ready:"
echo "1) 本地执行: ssh -CNg -L 5900:127.0.0.1:5900 root@sg.joveso.com"
echo "2) 用 VNC Viewer 连接: 127.0.0.1:5900"

