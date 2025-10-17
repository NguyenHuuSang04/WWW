package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model;

import java.time.LocalDate;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: Scores
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Score {
    private String scoreId;
    private Student student;
    private String subjectName;
    private double score;
    private String semester;
    private LocalDate exemDate;
    private String scoreType;
    private String notes;

    public Score() {
    }

    public Score(String scoreId, Student student, String subjectName, double score, String semester, LocalDate exemDate, String scoreType, String notes) {
        this.scoreId = scoreId;
        this.student = student;
        this.subjectName = subjectName;
        this.score = score;
        this.semester = semester;
        this.exemDate = exemDate;
        this.scoreType = scoreType;
        this.notes = notes;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public LocalDate getExemDate() {
        return exemDate;
    }

    public void setExemDate(LocalDate exemDate) {
        this.exemDate = exemDate;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}