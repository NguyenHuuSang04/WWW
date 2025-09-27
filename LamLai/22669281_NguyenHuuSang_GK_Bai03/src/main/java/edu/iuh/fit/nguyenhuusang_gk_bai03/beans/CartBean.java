package edu.iuh.fit.nguyenhuusang_gk_bai03.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai03
 * @Class: CartBean
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class CartBean {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public CartBean(List<CartItemBean> items) {
        this.items = items;
    }

    public List<CartItemBean> getItems() {
        return items;
    }

    public void setItems(List<CartItemBean> items) {
        this.items = items;
    }

    // thêm sản phẩm
    public void addProduct(Product p) {
        for (CartItemBean item : items) {// chạy hết item trong giỏ hàng
            if (item.getProduct().getId() == p.getId()) { // kiểm tra xem item đó đã tồn tại trong bean chưa
                item.setQuantity(item.getQuantity() + 1); // nếu tồn tại rồi thì tăng quality + 1
                return;
            }
        }

        items.add(new CartItemBean(p, 1));// nếu item chưa có trong giỏ thì thêm item đó vào với quality là 1
    }

    // xóa sản phẩm
    public void removeProduct(int productID) {
        items.removeIf(item -> item.getProduct().getId() == productID);
    }

    // cập nhật số lượng
    public void updateProduct(int productID, int quality) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productID) {
                if (quality > 0) {
                    item.setQuantity(quality);
                } else {
                    removeProduct(productID);
                }
                return;
            }
        }

    }

    // tính tổng tiền
    public double getTotal() {
        double total = 0;
        for (CartItemBean itemBean : items) {
            total += itemBean.getSubtotal();
        }
        return total;
    }

    // xóa hết giỏ hàng
    public void clear() {
        items.clear();
    }
}