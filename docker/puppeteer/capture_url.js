/// <reference types="puppeteer" />
const puppeteer = require('puppeteer');

// 从命令行参数中获取URL
const args = process.argv.slice(2);
const url = args[0];

// 获取中国时区（东八区）时间戳字符串
function getCnTimeISOString() {
  const date = new Date();
  const utcTime = date.getTime() + (date.getTimezoneOffset() * 60000); // 转为 UTC 时间
  const cnTime = new Date(utcTime + (3600000 * 8)); // 加上 8 小时得到北京时间
  return cnTime.toISOString().replace(/[^0-9]/g, '');
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
    ],
  });

  const page = await browser.newPage();

  // 设置类似真实浏览器的 UA
  await page.setUserAgent(
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
  );

  // 访问网站
  try {
    await page.goto(url, { waitUntil: 'networkidle2' });
  } catch (err) {
    console.error('页面加载失败:', err.message);
    await browser.close();
    process.exit(1);
  }

  
  // 处理URL，用于文件名
  const urlForFilename = url
    .replace(/^https?:\/\//, '') // 去除 "http://" 或 "https://"
    .replace(/\//g, '_');        // 将 "/" 替换为 "_"

  // 截图查看效果
  // 构建截图路径
  // const screenshotPath = `images/${new Date().toISOString().replace(/[^0-9]/g, '')}_${urlForFilename}.png`;
  const timestamp = getCnTimeISOString();
  const screenshotPath = `images/${timestamp}_${urlForFilename}.png`;
  await page.screenshot({ path: screenshotPath });
  console.log(`${screenshotPath}`);

  await browser.close();
})();

