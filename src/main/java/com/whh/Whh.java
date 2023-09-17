package com.whh;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.InputType.file;

public class Whh {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       /*fos.write(98);
        fos.write(99);
        fos.write(100);*/
       /* byte[] bytes={99,100};
        fos.write(bytes,0,2);*/
      /*  int len=1;
        while((len=fis.read())!=-1){
            fos.write(len^23);
            System.out.print((char) len);
        }*/

       /* FileWriter fw = new FileWriter(new File("d:/whh.txt"));
        BufferedWriter bw =new BufferedWriter(new FileWriter("d:/whh"));
        FileWriter fw1= new FileWriter("d:/whh.txt");
        fw.write("whhh");
        fw.close();

*/     /* long l=System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:/远程链接问题解决.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/whh.txt"));
        byte[] bytes = new byte[1024 * 1024 * 5];
        int len=0;
        while((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        System.out.println("花时（毫秒）："+(System.currentTimeMillis()-l));
        bis.close();
        bos.close();*/
/*
        long l1=System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("d:/远程链接问题解决.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:/whh.txt"));
        char[] chars = new char[1024];
        String str=null;
        while((str=br.readLine())!=null){
            bw.write(str);
            //换行
            bw.write("\n");
        }
        System.out.println("花时（毫秒）："+(System.currentTimeMillis() - l1));
        br.close();
        bw.close();*/

      /*  //序列化
        Hero hx = new Hero("韩信", 10, 666666L);
        //创建对象流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/hero.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/hero.txt"));
        oos.writeObject(hx);
        Object o = ois.readObject();
        System.out.println(o.toString());
*/
      /*  //解压压缩包
        //实质是向压缩包里面读取文件或者目录
        File src=new File("d:/whh.zip");
        File dest=new File("d:/whh");
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));
        ZipEntry zipEntry=null;
        while((zipEntry=zis.getNextEntry())!=null){
            System.out.println(zipEntry.getName());
            //每一个文件都是一个ZipEntry对象
            if(zipEntry.isDirectory()){
                //在目的地创建相应的文件夹
                File file1 = new File(dest, zipEntry.toString());
                file1.mkdirs();

            }else{
                //是文件，不是目录，读写文件内容
                byte[] bytes = new byte[1024];
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(dest, zipEntry.toString())));
                int len;
                while((len=zis.read(bytes))!=-1){
                    bos.write(bytes,0,len);
                }
                bos.close();
                zis.closeEntry();
            }
        }
        zis.close();*/

        /*//压缩文件夹
        //压缩的源文件
        File src = new File("d:/aaa");
        //压缩包位置
        File dest = new File(src.getParentFile(),src.getName()+".zip");//d:/aaa.zip
        //压缩包位置(自动创建)
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
        //获取源文件里面的每一个文件（变成ZipEntry对象）放到压缩包里面去
        toZip(src,zos,src.getName());
        //关流
        zos.close();*/
    }
    public static  void toZip(File src,ZipOutputStream zos ,String name) throws IOException {
        //遍历源文件的里的每一个文件或文件夹
        File[] files = src.listFiles();
        for (File file1 : files) {
            if(file1.isFile()){
                //是文件 变成ZipEntry对象
                ZipEntry zipEntry = new ZipEntry(name + "/" + file1.getName());//aaa/xxx
                //放入实体
                zos.putNextEntry(zipEntry);
                //读取数据
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file1));
                byte[] bytes = new byte[1024];
                int len;
                while((len=bis.read(bytes))!=-1){
                    zos.write(bytes,0,len);
                }
                bis.close();
                zos.closeEntry();
            }else {
                //不是文件，而是文件夹，递归
                toZip(file1,zos,name+"/"+file1.getName());
            }
        }
    }
}