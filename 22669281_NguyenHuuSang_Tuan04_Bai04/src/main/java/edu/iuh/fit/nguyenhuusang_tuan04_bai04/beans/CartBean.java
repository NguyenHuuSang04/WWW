package edu.iuh.fit.nguyenhuusang_tuan04_bai04.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: CartBean
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class CartBean {
    private List<CartItemBean> items = new ArrayList<>();

    public List<CartItemBean> getItems() {
        return items;
    }

    public void addBook(Book book, int quantity) {
        for (CartItemBean item : items) {
            if (item.getBook().getId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItemBean(book, quantity));
    }

    public void removeBook(String bookId) {
        items.removeIf(item -> item.getBook().getId().equals(bookId));
    }

    public void updateBookQuantity(String bookId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItemBean::getTotal).sum();
    }

    // xóa hết giỏ haàng
    public void clear() {
        items.clear();
    }
}

