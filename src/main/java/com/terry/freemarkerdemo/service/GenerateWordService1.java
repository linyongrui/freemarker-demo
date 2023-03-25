package com.terry.freemarkerdemo.service;

import com.terry.freemarkerdemo.dto.ExamInfoDto;
import com.terry.freemarkerdemo.dto.StudentDto;
import com.terry.freemarkerdemo.util.FreemarkerUtil1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenerateWordService1 {

    private static Logger logger = LoggerFactory.getLogger(GenerateWordService1.class);

    private List<StudentDto> getParamByDb() {
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setName("林小明");
        studentDto1.setGender("男");
        studentDto1.setGrade("一年级");
        List<ExamInfoDto> examInfoDtos1 = new ArrayList<>();
        examInfoDtos1.add(new ExamInfoDto("语文", "90"));
        examInfoDtos1.add(new ExamInfoDto("数学", "99"));
        examInfoDtos1.add(new ExamInfoDto("英语", "95"));
        studentDto1.setExamInfoList(examInfoDtos1);

        StudentDto studentDto2 = new StudentDto();
        studentDto2.setName("陈小红");
        studentDto2.setGender("女");
        studentDto2.setGrade("二年级");
        List<ExamInfoDto> examInfoDtos2 = new ArrayList<>();
        examInfoDtos2.add(new ExamInfoDto("语文", "97"));
        examInfoDtos2.add(new ExamInfoDto("数学", "90"));
        examInfoDtos2.add(new ExamInfoDto("英语", "99"));
        studentDto2.setExamInfoList(examInfoDtos2);

        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList.add(studentDto1);
        studentDtoList.add(studentDto2);
        return studentDtoList;
    }

    //不能为null，不能有<字符
    private String getDefaultValue(Object value) {
        return value == null ? "" : value.toString().replaceAll("<", "&lt;");
    }

    private Map<String, Object> getDataMap() {
        List<StudentDto> studentDtos = this.getParamByDb();
        Map<String, Object> dataMap = new HashMap<>();
        for (StudentDto studentDto : studentDtos) {
            studentDto.setName(getDefaultValue(studentDto.getName()));
            studentDto.setGender(getDefaultValue(studentDto.getGender()));
            studentDto.setGrade(getDefaultValue(studentDto.getGrade()));
            for (ExamInfoDto examInfoDto : studentDto.getExamInfoList()) {
                examInfoDto.setSubject(getDefaultValue(examInfoDto.getSubject()));
                examInfoDto.setScore(getDefaultValue(examInfoDto.getScore()));
            }
        }
        dataMap.put("studentList", studentDtos);
        return dataMap;
    }

    public void generateWord() {
        Map<String, Object> dataMap = getDataMap();
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
