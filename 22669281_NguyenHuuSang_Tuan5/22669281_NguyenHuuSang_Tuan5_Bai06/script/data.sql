-- Tạo database
CREATE DATABASE IF NOT EXISTS QUANLYDANHMUC;
USE QUANLYDANHMUC;

-- Tạo bảng DANHMUC
CREATE TABLE IF NOT EXISTS DANHMUC (
    MADM VARCHAR(10) PRIMARY KEY,
    TENDANHMUC VARCHAR(100) NOT NULL,
    NGUOIQUANLY VARCHAR(100),
    GHICHU VARCHAR(255)
);

-- Tạo bảng TINTUC
CREATE TABLE IF NOT EXISTS TINTUC (
    MATT VARCHAR(10) PRIMARY KEY,
    TIEUDE VARCHAR(200) NOT NULL,
    NOIDUNGTT VARCHAR(255) NOT NULL,
    LIENKET VARCHAR(255) NOT NULL,
    MADM VARCHAR(10) NOT NULL,
    FOREIGN KEY (MADM) REFERENCES DANHMUC(MADM)
);

-- Thêm dữ liệu mẫu cho DANHMUC
INSERT INTO DANHMUC (MADM, TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES
('DM01', 'Thoi su', 'Nguyen Van A', 'Chuyen muc tin tuc thoi su'),
('DM02', 'The thao', 'Tran Thi B', 'Chuyen muc tin tuc the thao'),
('DM03', 'Giai tri', 'Le Van C', 'Chuyen muc giai tri tong hop');

-- Thêm dữ liệu mẫu cho TINTUC
INSERT INTO TINTUC (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES
('TT01', 'Viet Nam vo dich SEA Games', 'Doan the thao Viet Nam da vo dich SEA Games 32 tai Campuchia.', 'https://kenh14.vn/sea-games-32-ky-dai-hoi-thanh-cong-nhat-trong-lich-su-khi-the-thao-viet-nam-thi-dau-o-nuoc-ngoai-20230517185649533.chn', 'DM02'),
('TT02', 'Gia xang tang manh', 'Gia xang trong nuoc tiep tuc tang cao vao dau thang 9.', 'https://baomoi.com/gia-xang-dau-hom-nay-19-9-xang-dau-trong-nuoc-dong-loat-tang-gia-c53274333.epi', 'DM01'),
('TT03', 'Chuong trinh truyen hinh moi', 'Kenh VTV3 ra mat chuong trinh giai tri moi vao cuoi tuan.', 'https://vtv.vn/truyen-hinh/vtv3-thay-ao-moi-bang-loat-chuong-trinh-giai-tri-2025030714203085.htm', 'DM03'),
('TT04', 'Hanh trinh Olympic Paris', 'Doan the thao Viet Nam len duong du Olympic Paris 2024.', 'https://dantri.com.vn/the-thao/doan-the-thao-viet-nam-len-duong-du-olympic-paris-2024-20240724072324891.htm', 'DM02'),
('TT05', 'Ky hop Quoc hoi', 'Quoc hoi khai mac ky hop thu 2 nam 2025.', 'https://quochoi.vn/tintuc/Pages/tin-hoat-dong-cua-quoc-hoi.aspx?ItemID=92554', 'DM01');

-- Kiểm tra dữ liệu
SELECT * FROM DANHMUC;
SELECT * FROM TINTUC;