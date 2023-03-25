package com.terry.freemarkerdemo.service;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class GetResourceFileService {
    public void getResouceFile() {

        /*
         * 非java -jar方式运行，可以获得resources正确的绝对路径;
         * resourcesPath:D:/freemarker-demo/target/classes/templates/template.docx
         *
         * java -jar方式运行,不可以获得resources正确的绝对路径，因为jar是当做一个文件，而不是文件夹;
         * resourcesPath:file:/D:/freemarker-demo/target/freemarker-demo-1.0.jar!/BOOT-INF/classes!/templates/template.docx
         * */
        String resourcesPath = GetResourceFileService.class.getResource("/templates/generateWord2/template.docx").getPath();
        System.out.println("resourcesPath:" + resourcesPath);

        /*
         * 非java -jar方式运行，可得到资源的正确绝对路径，所以可以通过new File()转成File对象;
         * resourcesFile:true
         *
         * java -jar方式运行，不可以获得resources正确的绝对路径，不可通过new File()转成File对象;
         * resourcesFile:false
         * */
        File resourcesFile = new File(resourcesPath);
        System.out.println("resourcesFile:" + resourcesFile.exists());

        /*
         * java -jar方式运行，
         * 不能用getResource方法（返回URL，再通过getPath获得绝对路径，再new File获得File对象），
         * 可以用getResourceAsStream方法（返回InputStream，再写入一个新建的File，由此获得File对象）
         *
         * getResourceAsStream有两种使用方式:
         * 1.TestClass.class.getResourceAsStream(String path);
         *      path开头不带"/",那么就是从当前class文件的路径下找文件
         *      path开头带"/",那么就是从类路径.classpath中去找文件
         * 2.TestClass.class.getClassLoader.getResourceAsStream(String path);
         *      path中不能带/,因为这个的意思就是在类路径classpath中去找文件
         *
         * 可以先通过new File()新建本地文件temp.docx或者createTempFile()新建本地文件temp10099794038854241516.docx，
         * 再将获取到的InputStream写入本地文件,之后就直接读取本地文件即可。
         *
         * 该本地文件只是为了copy出来临时使用，可以加templateFile.deleteOnExit();程序结束后会自动删除该本地文件，前提是该文件对应的IO流要close。
         * */
        File templateFile = new File("./temp.docx");
//        File templateFile = File.createTempFile("temp", ".docx", new File("."));
        try (
                InputStream templateFileInputStream = GetResourceFileService.class.getResourceAsStream("/templates/generateWord2/template.docx");
                OutputStream outStream = new FileOutputStream(templateFile);
        ) {
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = templateFileInputStream.read(buffer, 0, 8192)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
