CREATE DATABASE UploadFileServletDB;
USE UploadFileServletDB;

CREATE TABLE contacts (
    contact_id INT AUTO_INCREMENT PRIMARY KEY, -- tự tăng trong MariaDB
    first_name VARCHAR(45) DEFAULT NULL,       -- VARCHAR thay cho NVARCHAR
    last_name VARCHAR(45) DEFAULT NULL,
    photo LONGBLOB                             -- LONGBLOB thay cho image
);
