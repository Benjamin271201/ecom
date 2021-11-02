package com.nashtech.ecommerce.security;

public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write"),
    BRAND_READ("brand:read"),
    BRAND_WRITE("brand:write"),
    CART_READ("brand:read"),
    CART_WRITE("brand:write"),
    CART_DETAIL_READ("brand:read"),
    CART_DETAIL_WRITE("brand:write"),
    CATEGORY_READ("brand:read"),
    CATEGORY_WRITE("brand:write"),
    LIKED_PRODUCT_READ("brand:read"),
    LIKED_PRODUCT_WRITE("brand:write"),
    LIKED_REVIEW_READ("brand:read"),
    LIKED_REVIEW_WRITE("brand:write"),
    PRODUCT_READ("brand:read"),
    PRODUCT_WRITE("brand:write"),
    PRODUCT_IMAGE_READ("brand:read"),
    PRODUCT_IMAGE_WRITE("brand:write"),
    REVIEW_READ("brand:read"),
    REVIEW_WRITE("brand:write"),
    TRANSACTION_READ("brand:read"),
    TRANSACTION_WRITE("brand:write");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
