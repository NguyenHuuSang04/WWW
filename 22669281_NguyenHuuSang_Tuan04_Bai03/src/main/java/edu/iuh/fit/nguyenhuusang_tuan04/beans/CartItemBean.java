package edu.iuh.fit.nguyenhuusang_tuan04.beans;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04
 * @Class: CartItemBean
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class CartItemBean {
    private Product product;
    private int quantity;

    public CartItemBean() {
    }

    public CartItemBean(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemBean{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public double getSubtotal() {
       return product.getPrice() * quantity;
    }
}