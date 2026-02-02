#!/bin/bash
# 检测当前 shell 并设置补全忽略大小写
if [[ -n "$ZSH_VERSION" ]]; then
  # Zsh
  autoload -Uz compinit && compinit
  zstyle ':completion:*' matcher-list 'm:{a-zA-Z}={A-Za-z}'
elif [[ -n "$BASH_VERSION" ]]; then
  # Bash
  bind 'set completion-ignore-case on'
fi

# 定义to_container的补全函数
_to_container() {
  local cur prev opts # 声明三个局部变量， cur表示当前输入的命令， prev表示前一个输入的命令， opts表示可选的命令
  COMPREPLY=() # 初始化 COMPREPLY 数组为空。COMPREPLY 是 Bash 内置的数组，用于存储自动补全建议。
  cur="${COMP_WORDS[COMP_CWORD]}" # 将当前正在补全的单词赋值给 cur, COMP_WORDS 是 Bash 内置的数组，用于存储用户输入的命令和参数。COMP_CWORD 表示当前输入的命令的索引位置。
  prev="${COMP_WORDS[COMP_CWORD-1]}" # 将前一个单词赋值给 prev

  if [[ ${cur} == -* ]] ; then # 检查当前单词是否以 - 开头。这个条件用于处理选项（如 -h, --help 等）
    return 0 # 返回 0 表示成功生成补全建议
  fi

  if [ "${prev}" != "docker" ]; then
    opts="$(grep -v "^#" /etc/hosts | grep -v -e 127.0.0.1 -e localhost -e broadcasthost | awk '{print $2}') docker"
    if [ -a ~/.ssh/config ]; then
      _opts=`cat ~/.ssh/config | grep HostName | awk '{print $2}'`
      opts="$opts $_opts"
    fi
  else # Check if Docker is running, macOS 用 ps aux
    if [ -z "$(ps aux | grep dockerd | grep -v grep | awk '{print $2}')" ]; then
        echo "$opts DOCKER IS NOT RUNNING"
    else
      _opts=`docker ps | grep -v "^CONTAINER" | awk '{print $NF}'` # 定义可选的补全选项，这些选项是 test.sh 可接受的参数
      opts="$opts $_opts"
    fi
  fi

  COMPREPLY=( $(compgen -W "${opts}" -- ${cur}) ) # 生成补全建议，并存储在 COMPREPLY 数组中
  return 0
}

complete -F _to_container to # 注册
complete -F _to_container to_container # 注册
