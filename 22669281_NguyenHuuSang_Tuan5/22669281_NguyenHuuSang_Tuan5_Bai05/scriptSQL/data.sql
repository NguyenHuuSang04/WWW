CREATE DATABASE IF NOT EXISTS tuan04_bai05;
USE tuan04_bai05;

CREATE TABLE IF NOT EXISTS departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department_id INT NOT NULL,
    salary DOUBLE NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES 
('Giam Doc'),
('Ke Toan'),
('Phat trien'),
('Ke hoach Vat tu'),
('Phong Quan Tri');

INSERT INTO employees (name, salary, department_id) VALUES
('Admin', 1212.00, 2),
('Nguyen Van A', 1500.00, 1),
('Tran Thi B', 1350.00, 3),
('Le Van C', 1100.00, 2),
('Pham Van D', 1450.00, 4),
('Dang Thi E', 1230.00, 5);