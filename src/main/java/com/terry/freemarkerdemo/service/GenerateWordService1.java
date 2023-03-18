package com.terry.freemarkerdemo.service;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class GenerateWordService1 {
    public void getResouceFile() {

        String resourcesPath = GenerateWordService1.class.getClassLoader().getResource("templates/template.docx").getPath();
        System.out.println("resourcesPath:" + resourcesPath);
/*
        //非java -jar方式运行，可以获得resources正确的绝对路径;
        //java -jar方式运行，不可以获得resources正确的绝对路径，因为jar是当做一个文件，而不是文件夹;
        //java -jar方式运行获取到的路径类似：file:/D:/cims-doc-param-svc/target/cims-doc-param-svc-1.0.jar!/BOOT-INF/classes!/
        String resourcesPath = GenerateWordService1.class.getClassLoader().getResource("").getPath();

        //非java -jar方式运行，可以通过resourcesPath拼接具体的资源文件路径，得到资源的正确绝对路径，进而可以直接转成File;
        //java -jar方式运行，不可以获得resources正确的绝对路径。
        String templateZipPath = resourcesPath + "templates/templates.docx";
        File templateZipFile = new File(templateZipPath);

        //java -jar方式运行，获取resources文件不能用getResource方法，要用getResourceAsStream方法。获取到的是InputStream
        InputStream templateZipInputStream = GenerateWordService1.class.getClassLoader().getResourceAsStream("templates/paramInstrution/paramInstrution.zip");

        //非java -jar方式运行，将获取到的InputStream转化成File，可以通过new File，手动写入templateZipInputStream，生成的temp.zip是正常的。
        //java -jar方式运行，将获取到的InputStream转化成File，不可以通过new File，手动写入templateZipInputStream，生成的temp.zip是空的。
        File zipFile = new File("./temp.zip");
        try {
            byte[] tempBuffer = new byte[templateZipInputStream.available()];
            templateZipInputStream.read(tempBuffer);
            OutputStream outStream = new FileOutputStream(docxFile);
            outStream.write(tempBuffer);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    //java -jar方式运行，将获取到的InputStream转化成File，可通过File.createTempFile(String prefix, String suffix, File directory)
    public static File asFile(InputStream inputStream) throws IOException {
        File tmp = File.createTempFile("temp", ".zip", new File("/"));
        OutputStream os = new FileOutputStream(tmp);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        return tmp;
    }

}
