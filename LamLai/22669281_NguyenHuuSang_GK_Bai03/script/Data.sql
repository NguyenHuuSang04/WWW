CREATE DATABASE shopdb;

USE shopdb;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    imgurl LONGTEXT
);

INSERT INTO products (model, description, quantity, price, imgurl) VALUES
('iPhone 15 Pro Max', 'Apple smartphone, 256GB, 5G, 3 camera', 12, 32990000, 'iphone15promax.jpg'),
('Samsung Galaxy S24 Ultra', 'Flagship Samsung, 256GB, 200MP camera', 10, 28990000, 'galaxys24ultra.jpg'),
('Xiaomi 14 Ultra', 'Xiaomi high-end, 12GB RAM, 512GB, Leica camera', 8, 24990000, 'xiaomi14ultra.jpg'),
('Oppo Find X7', 'Oppo flagship, 256GB, 2K AMOLED, 80W fast charge', 15, 20990000, 'findx7.jpg'),
('Macbook Air M2', 'Apple laptop, 13.6 inch, 8GB RAM, 256GB SSD', 7, 26990000, 'macbookairm2.jpg'),
('Dell XPS 13', 'Dell ultrabook, Intel Core i7, 16GB RAM, 512GB SSD', 9, 33990000, 'dellxps13.jpg'),
('iPad Pro 12.9', 'Apple tablet, M2 chip, 128GB, 120Hz display', 5, 31990000, 'ipadpro12.jpg'),
('Sony WH-1000XM5', 'Sony bluetooth headphone, ANC, 30h battery', 20, 8990000, 'sonyxm5.jpg'),
('Apple Watch Series 9', 'Apple smartwatch, GPS, 41mm, new sensor', 12, 11990000, 'applewatch9.jpg'),
('Logitech MX Master 3S', 'Wireless mouse, ergonomic, 8000 DPI, USB-C', 25, 1999000, 'mxmaster3s.jpg');