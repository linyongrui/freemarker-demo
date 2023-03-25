package com.terry.freemarkerdemo.dto;

public class ExamInfoDto {
    private String subject;
    private String score;

    public ExamInfoDto(String subject, String score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
