package edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.model;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De1_BaiThem
 * @Class: Account
 * @Tạo vào ngày: 9/29/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Account {
    private String account_number; // là dãy số để định danh cho 1 tài khoản. Dãy số có chiều dài 10 ký tự
    private String owner_name; //Họ tên chủ tìa khoản, tối đa 155 ký tự
    private String card_number; // Số thẻ gắn liền với tài khoản
    private String owner_address; // địa chỉ của chủ tải khoản
    private double amount; // số tiêền trong tài khoản. Kiểu số thực

    public Account() {
    }

    public Account(String account_number, String owner_name, String card_number, String owner_address, double amount) {
        this.account_number = account_number;
        this.owner_name = owner_name;
        this.card_number = card_number;
        this.owner_address = owner_address;
        this.amount = amount;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getOwner_address() {
        return owner_address;
    }

    public void setOwner_address(String owner_address) {
        this.owner_address = owner_address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}