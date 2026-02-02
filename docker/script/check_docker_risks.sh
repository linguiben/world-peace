#!/bin/bash
echo "[*] 正在检查 Docker 容器的高风险配置..."

containers=$(docker ps -q)
if [ -z "$containers" ]; then
    echo "[OK] 当前没有正在运行的容器。"
    exit 0
fi

risk_found=0

for cid in $containers; do
    echo "----------------------------------------"
    echo "[*] 检查容器 ID: $cid"
    name=$(docker inspect --format='{{.Name}}' "$cid" | sed 's/\///')
    echo "容器名称: $name"

    # 检查是否使用 privileged 模式
    privileged=$(docker inspect --format='{{.HostConfig.Privileged}}' "$cid")
    if [ "$privileged" == "true" ]; then
        echo "[!] ⚠️ 发现容器运行在 --privileged 模式下！"
        risk_found=1
    fi

    # 检查是否使用 host 网络
    network=$(docker inspect --format='{{.HostConfig.NetworkMode}}' "$cid")
    if [ "$network" == "host" ]; then
        echo "[!] ⚠️ 容器使用了 host 网络模式！"
        risk_found=1
    fi

    # 检查是否使用 host pid
    pid_mode=$(docker inspect --format='{{.HostConfig.PidMode}}' "$cid")
    if [ "$pid_mode" == "host" ]; then
        echo "[!] ⚠️ 容器使用了 host PID 命名空间！"
        risk_found=1
    fi

    # 检查是否挂载主机根目录
    mounts=$(docker inspect --format='{{range .Mounts}}{{.Source}}{{"\n"}}{{end}}' "$cid")
    if echo "$mounts" | grep -qE '^/$'; then
        echo "[!] ⚠️ 容器挂载了宿主机根目录 /"
        risk_found=1
    fi

    # 检查是否挂载 Docker socket
    if echo "$mounts" | grep -q "/var/run/docker.sock"; then
        echo "[!] ⚠️ 容器挂载了 /var/run/docker.sock，可能控制主机上的 Docker"
        risk_found=1
    fi

    # 检查是否以 root 用户运行
    user=$(docker inspect --format='{{.Config.User}}' "$cid")
    if [ -z "$user" ] || [ "$user" == "root" ]; then
        echo "[!] ⚠️ 容器以 root 用户运行！"
        risk_found=1
    fi
done

echo "----------------------------------------"
if [ "$risk_found" -eq 1 ]; then
    echo "[!!] 🚨 发现一个或多个高风险配置，请立即审查并修复。"
else
    echo "[OK] 所有容器配置无明显高风险。"
fi
