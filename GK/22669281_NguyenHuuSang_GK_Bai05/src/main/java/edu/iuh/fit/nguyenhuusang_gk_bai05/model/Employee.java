package edu.iuh.fit.nguyenhuusang_gk_bai05.model;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: employess
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Employee {
    private String id;
    private String name;
    private Department departments;
    private double salary;

    public Employee() {
    }

    public Employee(String id, String name, Department departments, double salary) {
        this.id = id;
        this.name = name;
        this.departments = departments;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartments() {
        return departments;
    }

    public void setDepartments(Department departments) {
        this.departments = departments;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employess{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", departments=" + departments +
                ", salary=" + salary +
                '}';
    }
}