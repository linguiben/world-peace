# 使用带 Java 运行环境的基础镜像（你可以换成具体的 jre 镜像）
FROM ubuntu:22.04

# 避免交互安装提示
ENV DEBIAN_FRONTEND=noninteractive

# 安装依赖包
# ubuntu 24.10 之后: libasound2 -> libasound2t64
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    ca-certificates \
    wget \
    python3 \
    python3-pip \
    openjdk-11-jre-headless \
    fonts-liberation \
    libappindicator3-1 \
    libasound2 \
    libatk-bridge2.0-0 \
    libatk1.0-0 \
    libcups2 \
    libdbus-1-3 \
    libgdk-pixbuf2.0-0 \
    libnspr4 \
    libnss3 \
    libx11-xcb1 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    xdg-utils \
    libgbm-dev \
    libxshmfence1 \
    libgtk-3-0 \
    --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*

# 安装 Node.js 20
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g npm

# 设置 Puppeteer 版本和跳过 Chromium 下载（如要精简镜像）
ENV PUPPETEER_SKIP_DOWNLOAD=false

# 创建应用目录
WORKDIR /app

# 安装 Puppeteer
COPY package.json package-lock.json* ./
RUN npm install

# 拷贝代码
COPY . .

# 入口命令（可根据项目调整）
CMD ["node", "sina.js"]