package edu.iuh.fit.nguyenhuusang_gk_bai06.model;
/**
    * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
    * @Class: TinTuc
    * @Tạo vào ngày: 9/26/2025
    * @Tác giả: Nguyen Huu Sang
*/
public class TinTuc {
    private String maTT;
    private String tieuDe;
    private String noiDungTT;
    private String lienKet;
    private DanhMuc danhMuc;

    public TinTuc() {
    }

    public TinTuc(String maTT, String tieuDe, String noiDungTT, String lienKet, DanhMuc danhMuc) {
        this.maTT = maTT;
        this.tieuDe = tieuDe;
        this.noiDungTT = noiDungTT;
        this.lienKet = lienKet;
        this.danhMuc = danhMuc;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDungTT() {
        return noiDungTT;
    }

    public void setNoiDungTT(String noiDungTT) {
        this.noiDungTT = noiDungTT;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
}