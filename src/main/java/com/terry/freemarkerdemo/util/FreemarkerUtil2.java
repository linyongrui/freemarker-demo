package com.terry.freemarkerdemo.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FreemarkerUtil2 {
    private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil2.class);
    private static final String CHARSET_NAME_UTF_8 = "utf-8";

    public static Configuration getConfiguration(String basePackagePath) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding(CHARSET_NAME_UTF_8);
        configuration.setClassForTemplateLoading(FreemarkerUtil2.class, basePackagePath);
        return configuration;
    }

    public static ByteArrayInputStream getFreemarkerContentInputStream(Map dataMap, String basePackagePath) throws IOException, TemplateException {
        Template template = getConfiguration(basePackagePath).getTemplate("document.xml");
        StringWriter swriter = new StringWriter();
        template.process(dataMap, swriter);
        return new ByteArrayInputStream(swriter.toString().getBytes(CHARSET_NAME_UTF_8));
    }

    public static File getTempeFileFromResource(String templateFilePrefix, String templateFileSuffix, String resourceFilePath) throws IOException {
        File tempFile = File.createTempFile(templateFilePrefix, templateFileSuffix, new File("."));
        tempFile.deleteOnExit();
        try (
                InputStream templateZipInputStream = FreemarkerUtil2.class.getClassLoader().getResourceAsStream(resourceFilePath);
                OutputStream os = new FileOutputStream(tempFile);
        ) {
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = templateZipInputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            logger.error("getTemplateZipFile Exception.", e);
            throw e;
        }
        return tempFile;
    }

    public static void generateWord(Map dataMap, String basePackagePath, String templatePath, String generateFileName) throws IOException, TemplateException {
        logger.info("Generating word document.");
        try (
                ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(generateFileName));
                ByteArrayInputStream documentInputStream = getFreemarkerContentInputStream(dataMap, basePackagePath);
                ZipFile zipFile = new ZipFile(getTempeFileFromResource("templateZipTempFile", ".zip", templatePath));
        ) {
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            int len = -1;
            byte[] buffer = new byte[1024];

            while (zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                if (next.toString().indexOf("media") < 0) {
                    zipout.putNextEntry(new ZipEntry(next.getName()));
                    if ("word/document.xml".equals(next.getName())) {
                        if (documentInputStream != null) {
                            while ((len = documentInputStream.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            documentInputStream.close();
                        }
                    } else {
                        while ((len = is.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        is.close();
                    }
                }
            }
        } catch (Exception e) {
            logger.error(generateFileName + " generate file Exception.", e);
            throw e;
        }
    }
}