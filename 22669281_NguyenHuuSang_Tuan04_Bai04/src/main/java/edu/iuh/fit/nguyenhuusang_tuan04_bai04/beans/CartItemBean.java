package edu.iuh.fit.nguyenhuusang_tuan04_bai04.beans;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: CartItem
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class CartItemBean {
    private Book book;
    private int quantity;

    public CartItemBean() {}
    public CartItemBean(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return book.getPrice() * quantity;
    }
}

