package edu.iuh.fit.entities;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT_2
 * @Enum: ProductCondition
 * @Tạo vào ngày: 11/3/2025
 * @Tác giả: Nguyen Huu Sang
 */
public enum ProductCondition {

    NEW("MỚi 100%"),
    REFURBISHED("Đã tân trang"),
    USED("Đã sử dụng");

    private String displayName;

    ProductCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}