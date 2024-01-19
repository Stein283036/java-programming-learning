package com.stein.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author njl
 * @date 2023/2/7
 */
public class HttpApp {
    public static void main(String[] args) throws IOException {
        h1();
    }

    public static void h1() throws IOException {
        URL url = new URL("https://www.baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accpet", "*/*");
        conn.connect();
        if (conn.getResponseCode() == 200) {
            // 获取请求内容
            InputStream is = conn.getInputStream();
            byte[] buf = new byte[2048];
            int read;
            while ((read = is.read(buf)) != -1) {
                System.out.println(new String(buf, 0, read));
            }
            // 获取请求头
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            for (String header : headerFields.keySet()) {
                System.out.println(header + headerFields.get(header));
            }
        }
    }
}
