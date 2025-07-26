```shell
# 配置镜像加速
# cat /etc/docker/daemon.json
{
  "registry-mirrors": ["https://0gwr3ij7.mirror.aliyuncs.com"]
}

# 重启docker
sudo systemctl daemon-reload
sudo systemctl restart docker

# 验证镜像加速
docker info | grep Mirrors -A 1
 Registry Mirrors:
  https://0gwr3ij7.mirror.aliyuncs.com/
```