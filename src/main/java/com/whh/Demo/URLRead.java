package com.whh.Demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;

public class URLRead {

    public static void myURLread(String urlString) {

        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            // 打开连接的输入流，并读取文件内容
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));



            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); 
            }

            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "hdfs://192.168.80.151:9000/whh.txt"; // HDFS文件路径
        myURLread(filePath);

    }
}
