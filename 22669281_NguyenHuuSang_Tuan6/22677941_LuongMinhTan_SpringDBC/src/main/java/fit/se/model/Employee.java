package fit.se.model;

public class Employee {
    private String empId;
    private String empName;
    private String email;
    private int age;
    private int status;
    private Department deptId;
    private double salary;

    public Employee() {
    }

    public Employee(String empId, String empName, String email, int age, int status, Department deptId, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.status = status;
        this.deptId = deptId;
        this.salary = salary;
    }

    public void setDeptId(Department deptId) {
        this.deptId = deptId;
    }

    public Department getDeptId() {
        return deptId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}