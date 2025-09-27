package edu.iuh.fit.nguyenhuusang_gk_bai03.beans;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai03
 * @Class: Product
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class Product {
    private int id;
    private String model;
    private String description;
    private int quantity;
    private double price;
    private String imgurl;

    public Product() {
    }

    public Product(int id, String model, String description, int quantity, double price, String imgurl) {
        this.id = id;
        this.model = model;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.imgurl = imgurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", quatitly=" + quantity +
                ", price=" + price +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}