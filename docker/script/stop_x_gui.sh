#!/usr/bin/env bash
set -euo pipefail

DISPLAY_NUM="${1:-99}"
DISPLAY=":${DISPLAY_NUM}"

echo "Stopping GUI services on ${DISPLAY} ..."

stop_by_pid() {
  local name="$1"
  local pids
  pids="$(pgrep -f "$name" || true)"
  if [[ -n "$pids" ]]; then
    echo "Found $name process(es):"
    echo "$pids"
    kill $pids || true
  else
    echo "No $name process found."
  fi
}

# 1) 优先停止 x11vnc
stop_by_pid "x11vnc.*-display ${DISPLAY}"
sleep 1

# 2) 停止 openbox
if pgrep -x openbox >/dev/null 2>&1; then
  echo "Stopping openbox ..."
  pkill -x openbox || true
else
  echo "No openbox process found."
fi
sleep 1

# 3) 停止当前 DISPLAY 的 Xvfb
stop_by_pid "Xvfb ${DISPLAY}"
sleep 1

# 4) 如果还没停干净，强制结束
force_stop() {
  local pattern="$1"
  local pids
  pids="$(pgrep -f "$pattern" || true)"
  if [[ -n "$pids" ]]; then
    echo "Force killing: $pattern"
    kill -9 $pids || true
  fi
}

force_stop "x11vnc.*-display ${DISPLAY}"
force_stop "Xvfb ${DISPLAY}"

# 5) 清理锁文件和 socket
LOCK_FILE="/tmp/.X${DISPLAY_NUM}-lock"
SOCKET_FILE="/tmp/.X11-unix/X${DISPLAY_NUM}"

if [[ -e "$LOCK_FILE" ]]; then
  echo "Removing lock file: $LOCK_FILE"
  rm -f "$LOCK_FILE"
fi

if [[ -e "$SOCKET_FILE" ]]; then
  echo "Removing socket file: $SOCKET_FILE"
  rm -f "$SOCKET_FILE"
fi

# 6) 清理 chromium-browser 进程 (chromium-browser太长了，pgrep -x 可能匹配不到，所以用 pgrep -f 匹配)
if pgrep -f "chromium-browser" >/dev/null 2>&1; then
  echo "Stopping chromium-browser ..."
  pkill -f "chromium-browser" || true
else
  echo "No chromium-browser process found."
fi

echo
echo "Remaining related processes:"
ps -ef | egrep "Xvfb|openbox|x11vnc" | grep -v grep || true

echo
echo "Done."
