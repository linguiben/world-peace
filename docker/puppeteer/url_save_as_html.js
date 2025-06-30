/// <reference types="puppeteer" />
const puppeteer = require('puppeteer');
const fs = require('fs');

// 从命令行参数中获取URL
const args = process.argv.slice(2);
const url = args[0];

// 获取中国时区（东八区）时间戳字符串
function getCnTimeISOString() {
  const now = new Date();
  const cnt = new Date(now.getTime() + 8 * 60 * 60 * 1000);
 return cnt.toISOString().replace(/[^0-9]/g, '').substring(0, 14);
}

if (!url) {
  console.error('❌ 请提供一个URL作为参数！');
  console.log('示例: node capture_url.js https://www.example.com');
  process.exit(1);
}

(async () => {
  const browser = await puppeteer.launch({
    headless: true, // 改成 true 就不显示窗口
    args: [
      '--no-sandbox',
      '--disable-setuid-sandbox',
      '--disable-blink-features=AutomationControlled',
      // '--proxy-server=http://127.0.0.1:8086'
    ],
  });

  const page = await browser.newPage();

  // 设置类似真实浏览器的 UA
  await page.setUserAgent(
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
  );

  // 设置视口大小
  await page.setViewport({
  width: 1280,
  height: 800
  });

  // 访问网站
  try {
    await page.goto(url, { waitUntil: 'networkidle2' });
    // await page.goto(url);
  } catch (err) {
    console.error('页面加载失败:', err.message);
    await browser.close();
    process.exit(1);
  }

  
  // 处理URL，用于文件名
  const urlForFilename = url
    .replace(/^https?:\/\//, '') // 去除 "http://" 或 "https://"
    .replace(/\//g, '_')         // 将 "/" 替换为 "_"
    .replace(/[^a-zA-Z0-9_]/g, ''); // 去除其他特殊字符

  //save as html
  const timestamp = getCnTimeISOString();
  const savedHtmlPath = `html/${timestamp}_${urlForFilename}.html`;

  // save as HTML
  const htmlContent = await page.content();
  fs.writeFileSync(savedHtmlPath, htmlContent, 'utf8');
  console.log(savedHtmlPath);

  await browser.close();
})();

