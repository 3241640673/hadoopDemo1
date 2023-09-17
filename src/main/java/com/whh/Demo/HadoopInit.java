package com.whh.Demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class HadoopInit {
    public FileSystem fs=null;
    @Before
    public  void init() throws Exception {
        // 创建Hadoop配置对象
        Configuration conf = new Configuration();
        // 访问hadoop的HDFS问的文件系统（namenode的地址和端口）
        conf.set("fs.defaultFS", "hdfs://192.168.80.151:9000");
        System.setProperty("HADOOP_USER_NAME", "root");
        // 创建Hadoop文件系统对象
        fs = FileSystem.get(conf);
    }
   @Test
    public  void upLoad() throws IOException {
        // 将本地文件上传到Hadoop集群
        Path localFilePath = new Path("");
        Path hdfsFilePath = new Path("");
        fs.copyFromLocalFile(localFilePath, hdfsFilePath);
        System.out.println("File uploaded.");
    }
@Test
    public void download() throws IOException {
        // 将本地文件上传到Hadoop集群
        Path localFilePath = new Path("");
        Path hdfsFilePath = new Path("");
        fs.copyToLocalFile(hdfsFilePath,localFilePath);
        System.out.println("File download.");
    }
    @Test
    public  void delete() throws IOException {
        fs.delete(new Path(""),true);
    }
    @Test
    public  void listFile() throws IOException {
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/usr/hadoop"), true);
        while(iterator.hasNext()){
            LocatedFileStatus fileStatus = iterator.next();
            //目录名称
            System.out.println(fileStatus.getPath().getName());
            //存储块大小
            System.out.println(fileStatus.getBlockSize());
            //文件权限
            System.out.println(fileStatus.getPermission());
            //文件长度
            System.out.println(fileStatus.getLen());
            //文件拥有者
            System.out.println(fileStatus.getOwner());
            //该文件所在块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation bl : blockLocations) {
                //长度
                System.out.println(bl.getLength());
                //偏移量
                System.out.println(bl.getOffset());
                //块的datanode地址列表
                String[] hosts = bl.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("-------------------------------------");
        }
    }

      /*
        // 在Hadoop集群上创建一个新文件夹
        Path newDirect = new Path("/usr/hadoop");
        fs.mkdirs(newDirect);
        System.out.println("new direct is created.");
}
         // 列出Hadoop集群上的文件和文件夹(根目录下的文件和文件夹)
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus status : fileStatuses) {
            System.out.println(status.getPath());
        }*/

     /*   // 删除Hadoop集群上的文件夹
        fs.delete(new Path("/whh.txt"),true);
        System.out.println("Folder deleted.");*/
        /*// 打开Hadoop文件系统对象
        FSDataInputStream open = fs.open(new Path("/whh.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(open));
        String line;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }*/
    }