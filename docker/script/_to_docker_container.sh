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

# 将此脚本保存为 _to_docker_container.sh 并放在合适的目录下
# 使用 source 命令加载脚本
# source ./_to_docker_container.sh
# 然后在终端中输入 to 并按 Tab 键进行补全
# 你可以在终端中输入 to 并按 Tab 键来测试补全功能

# 如何令补全函数在登录时自动生效:
# 最常见的是在 ~/.bashrc 中增加:
# User-defined completion functions
# if [ -f /root/Jupiter/._completion/_* ]; then . /root/Jupiter/._completion/_*; fi
