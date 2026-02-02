#!/bin/bash

echo "[*] 检查潜在恶意活动..."

# 检查可疑目录
echo "[*] 检查是否存在 /tmp/.ICE-unix 目录..."
if [ -d "/tmp/.ICE-unix" ]; then
    echo "[!] 可疑目录存在: /tmp/.ICE-unix"
    ls -la /tmp/.ICE-unix
fi

# 检查可疑文件执行记录
echo "[*] 检查 /proc 中是否记录了来自 /tmp 等路径的可执行程序..."
for pid in $(ps -eo pid --no-headers); do
    if [ -r /proc/$pid/exe ]; then
        exe_path=$(readlink -f /proc/$pid/exe)
        if [[ "$exe_path" =~ ^(/tmp|/dev/shm|/var/tmp) ]]; then
            echo "[!] 进程 $pid 正在执行路径：$exe_path"
        fi
    fi
done

# 检查 shell 历史记录是否包含 .onion、tor2web、doh.li 等关键字
echo "[*] 扫描 ~/.bash_history 中是否包含可疑网址关键词..."
grep -Ei "(onion|tor2web|tor2socks|doh.li|doh.pub|dns.sb)" ~/.bash_history && echo "[!] 检测到可疑命令历史！"

# 检查 curl 使用记录
echo "[*] 检查 curl 是否被用于 .onion 域访问..."
grep -r "curl" /tmp /dev/shm /var/tmp 2>/dev/null | grep ".onion" && echo "[!] 发现 .onion 使用记录！"

# 检查是否存在最近的执行文件名像 hash 的文件
echo "[*] 检查可疑 md5 文件名（20位以内）..."
find /tmp /dev/shm /var/tmp -maxdepth 1 -type f -executable -regextype posix-extended -regex '.*/[a-f0-9]{16,20}' -exec ls -l {} \;

# 检查系统是否能解析或连接 relay.tor2socks.in
echo "[*] 检查 DNS 是否能解析 relay.tor2socks.in ..."
host relay.tor2socks.in || echo "[OK] 当前 DNS 无法解析该地址"

echo "[*] 检查完成。请手动分析上方标记为 [!] 的部分。"
