#!/bin/bash
# 检查服务器是否执行过危险命令
# 保存为 check_malware.sh 并用 sudo 运行

SUSPICIOUS_IP="47.239.193.183"
SUSPICIOUS_PORT="60113"
TMP_FILE="/tmp/y9itOrOdaw"

echo "=== [1] 检查命令历史 ==="
grep -HnE "/dev/tcp|${SUSPICIOUS_IP}|${SUSPICIOUS_PORT}" /root/.bash_history ~/.bash_history 2>/dev/null

echo -e "\n=== [2] 检查/tmp目录可疑文件 ==="
if [ -f "$TMP_FILE" ]; then
    echo "发现可疑文件: $TMP_FILE"
    ls -l "$TMP_FILE"
else
    echo "未发现 $TMP_FILE"
fi

# 检查 /tmp 下的可执行文件
find /tmp -type f -perm -111 -exec ls -l {} \; 2>/dev/null

echo -e "\n=== [3] 检查当前网络连接 ==="
ss -antp | grep "${SUSPICIOUS_IP}:${SUSPICIOUS_PORT}" || echo "未发现连接到 ${SUSPICIOUS_IP}:${SUSPICIOUS_PORT} 的进程"

echo -e "\n=== [4] 检查系统日志中可疑记录 ==="
grep -R "${SUSPICIOUS_IP}" /var/log 2>/dev/null | head -n 20

echo -e "\n=== [5] 检查登录记录 ==="
last -n 10

echo -e "\n检查完成，请人工分析以上输出结果。"
echo "访问以下链接查询IP地址信息： https://qifu.baidu.com/?activeKey=SEARCH_IP&trace=apistore_ip_aladdin&activeId=SEARCH_IP_ADDRESS&ip="