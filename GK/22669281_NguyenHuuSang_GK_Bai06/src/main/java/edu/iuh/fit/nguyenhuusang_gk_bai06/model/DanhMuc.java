package edu.iuh.fit.nguyenhuusang_gk_bai06.model;
/**
    * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
    * @Class: DanhMuc
    * @Tạo vào ngày: 9/26/2025
    * @Tác giả: Nguyen Huu Sang
*/
public class DanhMuc {
    private String maDM;
    private String tenDM;
    private String nguoiQL;
    private String ghiChu;

    public DanhMuc() {
    }

    public DanhMuc(String maDM, String tenDM, String nguoiQL, String ghiChu) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.nguoiQL = nguoiQL;
        this.ghiChu = ghiChu;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getNguoiQL() {
        return nguoiQL;
    }

    public void setNguoiQL(String nguoiQL) {
        this.nguoiQL = nguoiQL;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}