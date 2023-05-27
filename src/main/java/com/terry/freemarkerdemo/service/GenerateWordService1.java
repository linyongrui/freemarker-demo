package com.terry.freemarkerdemo.service;

import com.terry.freemarkerdemo.util.DataUtill;
import com.terry.freemarkerdemo.util.FreemarkerUtil1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class GenerateWordService1 {

    private static Logger logger = LoggerFactory.getLogger(GenerateWordService1.class);

    public void generateWord() {
        Map<String, Object> dataMap = DataUtill.getDataMap();
        String templateName = "template.ftl";
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fileName = "./generate1-" + dtf2.format(LocalDateTime.now()) + ".doc";
        try {
            FreemarkerUtil1.generateWord(dataMap, templateName, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
