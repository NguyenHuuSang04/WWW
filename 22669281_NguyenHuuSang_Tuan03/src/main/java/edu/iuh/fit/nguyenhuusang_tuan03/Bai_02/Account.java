package edu.iuh.fit.nguyenhuusang_tuan03.Bai_02;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan03
 * @Class: Account
 * @Tạo vào ngày: 9/8/2025
 * @Tác giả: Nguyen Huu Sang
 */

import java.time.LocalDate;

public class Account {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday; // yyyy-MM-dd
    private boolean gender; // true: Male, false: Female

    public Account() {
    }

    public Account(String firstName, String lastName, String email, String password, LocalDate birthday, boolean gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", gender=" + (gender ? "Male" : "Female") +
                '}';
    }
}