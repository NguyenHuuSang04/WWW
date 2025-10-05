-- Tạo database mới
CREATE DATABASE IF NOT EXISTS employee_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE employee_db;

-- Tạo bảng Department
CREATE TABLE Department (
    deptId VARCHAR(10) PRIMARY KEY,
    deptName VARCHAR(100) NOT NULL
);

-- Tạo bảng Emploemployee_dbyee và liên kết với Department
CREATE TABLE Employee (
    empId VARCHAR(10) PRIMARY KEY,
    empName VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    age INT,
    status TINYINT,
    deptId VARCHAR(10),
    salary DECIMAL(10,2),
    FOREIGN KEY (deptId) REFERENCES Department(deptId)
);

-- Thêm dữ liệu mẫu cho Department
INSERT INTO Department (deptId, deptName) VALUES
('D001', 'IT'),
('D002', 'HR');

-- Thêm dữ liệu mẫu cho Employee
INSERT INTO Employee (empId, empName, email, age, status, deptId, salary) VALUES
('E001', 'Alice Packer', 'Alicepaker@gmail.com', 20, 1, 'D002', 1000);

-- Thêm dữ liệu cho bảng Department
INSERT INTO Department (deptId, deptName) VALUES
('D003', 'Kế toán');

-- Thêm dữ liệu cho bảng Employee
INSERT INTO Employee (empId, empName, email, age, status, deptId, salary) VALUES
('E002', 'Bob Smith', 'bob@gmail.com', 22, 1, 'D001', 1200),
('E003', 'Charlie Lee', 'charlie@gmail.com', 25, 0, 'D003', 900),
('E004', 'David Kim', 'david@gmail.com', 28, 1, 'D001', 2000);