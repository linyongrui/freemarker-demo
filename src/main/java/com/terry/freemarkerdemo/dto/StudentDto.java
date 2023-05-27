package com.terry.freemarkerdemo.dto;

import java.util.List;

public class StudentDto {
    private Long stuId;
    private String name;
    private String gender;
    private String grade;
    private List<ExamInfoDto> examInfoList;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<ExamInfoDto> getExamInfoList() {
        return examInfoList;
    }

    public void setExamInfoList(List<ExamInfoDto> examInfoList) {
        this.examInfoList = examInfoList;
    }
}
