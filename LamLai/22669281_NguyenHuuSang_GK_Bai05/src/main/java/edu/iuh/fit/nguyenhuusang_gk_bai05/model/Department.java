package edu.iuh.fit.nguyenhuusang_gk_bai05.model;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: departments
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Department {
    private String id;
    private String name;

    public Department() {
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Departments{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}