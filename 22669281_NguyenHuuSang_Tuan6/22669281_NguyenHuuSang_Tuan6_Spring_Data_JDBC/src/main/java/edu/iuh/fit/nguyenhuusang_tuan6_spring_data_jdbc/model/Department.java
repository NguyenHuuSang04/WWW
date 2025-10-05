package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.model;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_JDBC
 * @Class: Department
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Department {
    private String deptID;
    private String deptName;

    public Department() {
    }

    public Department(String deptID, String deptName) {
        this.deptID = deptID;
        this.deptName = deptName;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}