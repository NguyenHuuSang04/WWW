package edu.iuh.fit.nguyenhuusang_tuan5_bai06.model;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan5_Bai06
 * @Class: DanhMuc
 * @Tạo vào ngày: 9/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DanhMuc {
    private String maDM;
    private String tenDanhMuc;
    private String nguoiQuanLy;
    private String ghiChu;

    public DanhMuc() {
    }

    public DanhMuc(String maDM, String tenDanhMuc, String nguoiQuanLy, String ghiChu) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
        this.nguoiQuanLy = nguoiQuanLy;
        this.ghiChu = ghiChu;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getNguoiQuanLy() {
        return nguoiQuanLy;
    }

    public void setNguoiQuanLy(String nguoiQuanLy) {
        this.nguoiQuanLy = nguoiQuanLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
                "maDM='" + maDM + '\'' +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                ", nguoiQuanLy='" + nguoiQuanLy + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}