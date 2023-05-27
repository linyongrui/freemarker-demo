package com.terry.freemarkerdemo.service;

import com.terry.freemarkerdemo.util.DataUtill;
import com.terry.freemarkerdemo.util.FreemarkerUtil2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class GenerateWordService2 {
    private static Logger logger = LoggerFactory.getLogger(GenerateWordService2.class);

    public void generateWord() {
        Map<String, Object> dataMap = DataUtill.getDataMap();
        String basePackagePath = "/templates/generateWord2";
        String templatePath = "templates/generateWord2/template.docx";
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String generateFileName = "./generate2-" + dtf2.format(LocalDateTime.now()) + ".docx";
        try {
            FreemarkerUtil2.generateWord(dataMap, basePackagePath, templatePath, generateFileName);
        } catch (Exception e) {
            logger.error("generate param instruction file Exception.", e);
        }
        logger.info("====generate param instruction file Exception end=====");
    }
}
