package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.model;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_JDBC
 * @Class: Employee
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Employee {
    private String empID;
    private String empName;
    private String email;
    private int age;
    private int status;
    private Department department;
    private double salary;

    public Employee() {
    }

    public Employee(String empID, String empName, String email, int age, int status, Department department, double salary) {
        this.empID = empID;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.status = status;
        this.department = department;
        this.salary = salary;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}