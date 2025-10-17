CREATE DATABASE bookdb;

USE bookdb;

-- Tạo bảng book
CREATE TABLE books (
    id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    imgurl LONGTEXT,
    description TEXT
);

-- Thêm 10 bản ghi mẫu
INSERT INTO books (id, title, author, price, quantity, imgurl, description) VALUES
('b01', 'Sổ tay viết văn', 'Tô Hoài', 99000, 10, 'so-tay-viet-van.jpg', 'Sổ tay hướng dẫn kỹ năng viết văn dành cho học sinh.'),
('b02', 'Nhật ký ma cà rồng 03 - Cơn Thịnh Nộ', 'L.J. Smith', 48000, 10, 'nhat-ky-ma-ca-rong-3.jpg', 'Tập 3 của bộ truyện Nhật ký ma cà rồng nổi tiếng.'),
('b03', 'Billy và Beth - 3 bí quyết của người thành đạt', 'Nhiều tác giả', 52000, 10, 'billy-beth.jpg', 'Cuốn sách truyền cảm hứng cho giới trẻ về thành công.'),
('b04', 'Bí ẩn một cái chết', 'Nguyễn Hồng Dung', 83000, 10, 'bi-an-mot-cai-chet.jpg', 'Tiểu thuyết trinh thám hấp dẫn của tác giả Việt Nam.'),
('b05', 'Kẻ trộm sách', 'Markus Zusak', 71000, 10, 'ke-trom-sach.jpg', 'Tác phẩm nổi tiếng về chiến tranh và tình yêu sách vở.'),
('b06', 'Cơm & Phở', 'Xuân Sách', 120000, 10, 'com-pho.jpg', 'Cuốn sách hài hước về ẩm thực Việt Nam.'),
('b07', 'Đắc nhân tâm', 'Dale Carnegie', 90000, 20, 'dac-nhan-tam.jpg', 'Quyển sách kỹ năng sống kinh điển.'),
('b08', 'Tuổi trẻ đáng giá bao nhiêu', 'Rosie Nguyễn', 75000, 15, 'tuoi-tre-dang-gia-bao-nhieu.jpg', 'Sách truyền cảm hứng cho giới trẻ Việt.'),
('b09', 'Hoàng tử bé', 'Antoine de Saint-Exupéry', 67000, 8, 'hoang-tu-be.jpg', 'Truyện thiếu nhi nổi tiếng toàn thế giới.'),
('b10', 'Nhà giả kim', 'Paulo Coelho', 80000, 12, 'nha-gia-kim.jpg', 'Tác phẩm kinh điển về hành trình tìm kiếm ước mơ.');


