# Inspect IPP

## 1. What is the resulting value of i?
```java
int testMethod() {
    int i = 0;
    i = i++; // i=(i++) is the same
    // What is the resulting value of i ??
    // print i = ??
}
```

## 2.The Result
| c  version 4.4.7                                                          | c version 4.8.5                                                         |
|---------------------------------------------------------------------------|-------------------------------------------------------------------------|
| <img src="imgs/c.4.4.7.png" alt="c.4.4.7.png" width="720" height="360">   | <img src="imgs/c.4.8.5.png" alt="c.4.8.5.png" width="600" height="360"> |
| <img src="imgs/java.1.0.png" alt="java.1.0.png" width="720" height="360"> | <img src="imgs/java.21.png" alt="java.21.png" width="720" height="360"> |
| <img src="imgs/shell.png" width="480" height="240">                       | <img src="imgs/bat.png" width="480" height="240">                       |
| <img src="imgs/py.png" width="480" height="240">                          |                                                                         |

## 3.Decompiled
### 3.1. c
### 表格
| c  version 4.4.7                                              | c version 4.8.5 |
|---------------------------------------------------------------|-----------------|
| ```java [root@centos6 ~/Jupiter/c]#cat T01.c}``` | ```c int testMethod() {<br/> int i = 0; i = i++; return i; }```            |

### 3.1. c

<table>
  <tr>
    <td>
      Source Code
    </td>
    <td>
      Assembly Source Code
    </td>
  </tr>
  <tr>
    <td>
      <pre><code class="language-c">[root@centos6 ~/Jupiter/c]#cat T01.c
#include <stdio.h>
int main(void) {
    int i = 0;
    i = i++;
    printf("i=%d\n",i);
    return 0;
}

[root@centos6 ~/Jupiter/c]#gcc T01.c -o T01

[root@centos6 ~/Jupiter/c]#./T01
i=1
</code></pre>
    </td>
    <td>
      <pre><code class="language-c">[root@centos6 ~/Jupiter/c]#gcc -S T01.c
[root@centos6 ~/Jupiter/c]#cat T01.s
.file    "T01.c"
.section    .rodata
.LC0:
.string    "i=%d\n"
.text
.globl main
.type    main, @function
main:
.LFB0:
.cfi_startproc
pushq    %rbp
.cfi_def_cfa_offset 16
.cfi_offset 6, -16
movq    %rsp, %rbp
.cfi_def_cfa_register 6
subq    $16, %rsp
movl    $0, -4(%rbp)
addl    $1, -4(%rbp)
movl    $.LC0, %eax
movl    -4(%rbp), %edx
movl    %edx, %esi
movq    %rax, %rdi
movl    $0, %eax
call    printf
movl    $0, %eax
leave
.cfi_def_cfa 7, 8
ret
.cfi_endproc
.LFE0:
.size    main, .-main
.ident    "GCC: (GNU) 4.4.7 20120313 (Red Hat 4.4.7-11)"
.section    .note.GNU-stack,"",@progbits
</code></pre>
    </td>
  </tr>
</table>
