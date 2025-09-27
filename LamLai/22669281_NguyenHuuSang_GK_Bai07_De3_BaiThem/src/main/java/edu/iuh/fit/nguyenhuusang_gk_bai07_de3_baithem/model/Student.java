package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model;

import java.time.LocalDate;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: Student
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Student {
    private String studentID;
    private String fullName;
    private String email;
    private String phone;
    private String className;
    private String major;
    private int enrollment_year;
    private String status;
    private LocalDate createDate;


    public Student() {
    }

    public Student(String studentID, String fullName, String email, String phone, String className, String major, int enrollment_year, String status, LocalDate createDate) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.className = className;
        this.major = major;
        this.enrollment_year = enrollment_year;
        this.status = status;
        this.createDate = createDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getEnrollment_year() {
        return enrollment_year;
    }

    public void setEnrollment_year(int enrollment_year) {
        this.enrollment_year = enrollment_year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}