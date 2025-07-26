# How to enable the completion functions?
## 1. save this script to `~/Jupiter/._completion/_to_docker_container.sh`
## 2. add the following to `~/.bashrc` or `~/.bash_profile`
## if [ -f /root/Jupiter/._completion/_* ]; then . /root/Jupiter/._completion/_*; fi
## export PATH=$PATH:~/Jupiter

# 定义to_container的补全函数
_to_container() {
    local cur prev opts # 声明三个局部变量， cur表示当前输入的命令， prev表示前一个输入的命令， opts表示可选的命令
    COMPREPLY=() # 初始化 COMPREPLY 数组为空。COMPREPLY 是 Bash 内置的数组，用于存储自动补全建议。
    cur="${COMP_WORDS[COMP_CWORD]}" # 将当前正在补全的单词赋值给 cur, COMP_WORDS 是 Bash 内置的数组，用于存储用户输入的命令和参数。COMP_CWORD 表示当前输入的命令的索引位置。
    prev="${COMP_WORDS[COMP_CWORD-1]}" # 将前一个单词赋值给 prev
    opts=`docker ps | grep -v "^CONTAINER" | awk '{print $NF}'` # 定义可选的补全选项，这些选项是 test.sh 可接受的参数

    if [[ ${cur} != -* ]] ; then # 检查当前单词是否以 - 开头。这个条件用于处理选项（如 -h, --help 等）
        COMPREPLY=( $(compgen -W "${opts}" -- ${cur}) ) # 生成补全建议，并存储在 COMPREPLY 数组中
        return 0 # 返回 0 表示成功生成补全建议
    fi
}
complete -F _to_container to # 将 _to_container 函数注册为 to 命令的补全函数
