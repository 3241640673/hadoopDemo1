package com.whh.Demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyFsDataInputStream extends FSDataInputStream {
   private static  FileSystem fileSystem=null;
    public MyFsDataInputStream(InputStream in) {
        super(in);
    }
    public  static void init() throws Exception {
        // 创建Hadoop配置对象
        Configuration conf = new Configuration();
        // 访问hadoop的HDFS问的文件系统（NameNode的地址和端口）
        conf.set("fs.defaultFS", "hdfs://192.168.80.151:9000");
        System.setProperty("HADOOP_USER_NAME", "root");
        // 创建Hadoop文件系统对象
        fileSystem = FileSystem.get(conf);
    }
      public static  void myRead(Path path) throws Exception {
          MyFsDataInputStream.init();
          FSDataInputStream open = fileSystem.open(path);
          BufferedReader br = new BufferedReader(new InputStreamReader(open));
          //读取内容（一行）
          String line;
          System.out.println("文件内容为");
          while ((line = br.readLine()) != null) {
              System.out.println(line);
          }
      }
    public static void main(String[] args) throws Exception {
        //创建文件目录
        Path path = new Path("/whh.txt");
        fileSystem.mkdirs(path);
        myRead(path);
    }


}
