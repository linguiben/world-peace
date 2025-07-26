package com.jupiter.admin.controllor;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/23
 */

public class UnsafeHttpClientExample {

//    public static void main(String[] args) throws Exception {
//        // 1. 创建一个信任所有证书的 TrustManager
//        TrustManager[] trustAllCerts = new TrustManager[]{
//                new X509TrustManager() {
//                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
//                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
//                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
//                }
//        };
//
//        // 2. 创建 SSLContext 并使用这个 TrustManager
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, trustAllCerts, new SecureRandom());
//
//        // 3. 创建一个忽略主机名验证的 HostnameVerifier
//        HostnameVerifier allHostsValid = (hostname, session) -> true;
//
//        // 4. 构造 HttpClient
//        HttpClient client = HttpClient.newBuilder()
//                .sslContext(sslContext)
//                .sslParameters(new SSLParameters())
//                .hostnameVerifier(allHostsValid)
//                .build();
//
//        // 5. 构建请求
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://your-https-server.com"))
//                .GET()
//                .build();
//
//        // 6. 发送请求
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("响应状态码: " + response.statusCode());
//        System.out.println("响应体: " + response.body());
//    }
}
