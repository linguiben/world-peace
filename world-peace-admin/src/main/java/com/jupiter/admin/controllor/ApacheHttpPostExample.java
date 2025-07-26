package com.jupiter.admin.controllor;

public class ApacheHttpPostExample {

//    public static void main(String[] args) throws Exception {
//        // 1. 构建忽略证书校验的 SSLContext
//        SSLContextBuilder sslBuilder = SSLContextBuilder.create();
//        sslBuilder.loadTrustMaterial(null, new TrustAllStrategy()); // 信任所有证书
//
//        SSLContext sslContext = sslBuilder.build();
//
//        // 2. 创建 HttpClient，设置忽略证书 + HostnameVerifier
//        try (CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                .build()) {
//
//            // 3. 创建 POST 请求
//            HttpPost post = new HttpPost("https://your-server.com/api/test");
//
//            // 设置请求头
//            post.setHeader("Content-Type", "application/json");
//            post.setHeader("Accept", "application/json");
//
//            // 设置请求体
//            String jsonBody = "{\"name\":\"Jupiter\",\"age\":30}";
//            post.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
//
//            // 4. 发送请求
//            HttpResponse response = httpClient.execute(post);
//
//            // 5. 读取响应内容
//            int statusCode = response.getCode();
//            System.out.println("Status Code: " + statusCode);
//
//            if (response.getEntity() != null) {
//                String responseBody = EntityUtils.toString(response.getEntity());
//                System.out.println("Response Body: " + responseBody);
//            } else {
//                System.out.println("Empty response body.");
//            }
//        }
//    }
}
