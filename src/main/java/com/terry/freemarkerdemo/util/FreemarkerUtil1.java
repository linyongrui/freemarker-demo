package com.terry.freemarkerdemo.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FreemarkerUtil1 {

    /**
     * 生成 word 文档方法
     *
     * @param dataMap      要填充的数据
     * @param templateName 模版名称
     * @param fileName     要输出的文件路径
     * @throws Exception 抛出的异常
     */
    public static void generateWord(Map<String, Object> dataMap, String templateName, String fileName) throws Exception {

        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
        configuration.setClassForTemplateLoading(FreemarkerUtil1.class, "/templates/generateWord1");

        // 设置FreeMarker生成Word文档所需要的模板
        Template tem = configuration.getTemplate(templateName, "UTF-8");
        // FreeMarker使用Word模板和数据生成Word文档
        // 创建一个Word文档的输出流
        try (
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
        ) {
            tem.process(dataMap, out);
        }
    }
}