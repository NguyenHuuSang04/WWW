package edu.iuh.fit.nguyenhuusang_tuan04_bai04.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: Cartbean
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class CartBean {
    private List<CartItemBean> books;

    public CartBean() {
        books = new ArrayList<>();
    }

    public CartBean(List<CartItemBean> books) {
        this.books = books;
    }

    public List<CartItemBean> getBooks() {
        return books;
    }

    public void setBooks(List<CartItemBean> books) {
        this.books = books;
    }

    // add
    public void addBook(Book b) {
        for(CartItemBean item:books) {
            if(item.getBook().getId().equals(b.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        books.add(new CartItemBean(b, 1));
    }

    // remove
    public void removeBook(String id) {
        books.removeIf(itemBean -> itemBean.getBook().getId().equals(id));
    }

    // update
    public void updateBook(String id, int quantity) {
        for(CartItemBean itemBean: books) {
            if(itemBean.getBook().getId().equals(id)) {
                if(quantity > 0) {
                    itemBean.setQuantity(quantity);
                } else {
                    removeBook(id);
                }
            }
        }
    }

    // tính tổng tiền
    public double getTotal(){
        double total = 0;
        for(CartItemBean itemBean: books) {
            total +=itemBean.getSubtotal();
        }
        return total;
    }

    // xóa hết giỏ hàng
    public void clear(){
        books.clear();
    }
}