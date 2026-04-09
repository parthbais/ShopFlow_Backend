package com.shopflow.config;

public final class AppConstants {

    private AppConstants() {}

    public static final String API_PREFIX = "/api/v1";

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE   = 10;

    public static final String DEFAULT_ROLE          = "USER";
    public static final String INITIAL_ORDER_STATUS  = "PENDING";

    public static final String MSG_USER_REGISTERED = "User registered successfully";
    public static final String MSG_USER_FOUND      = "User fetched successfully";
    public static final String MSG_USERS_FETCHED   = "Users fetched successfully";
    public static final String MSG_USER_DELETED    = "User deleted successfully";
    public static final String MSG_LOGIN_STUB      = "Login successful";

    public static final String MSG_PRODUCT_CREATED  = "Product created successfully";
    public static final String MSG_PRODUCT_FETCHED  = "Product fetched successfully";
    public static final String MSG_PRODUCTS_FETCHED = "Products fetched successfully";
    public static final String MSG_PRODUCT_UPDATED  = "Product updated successfully";
    public static final String MSG_PRODUCT_DELETED  = "Product deleted successfully";

    public static final String MSG_ORDER_CREATED  = "Order placed successfully";
    public static final String MSG_ORDER_FETCHED  = "Order fetched successfully";
    public static final String MSG_ORDERS_FETCHED = "Orders fetched successfully";
    public static final String MSG_ORDER_UPDATED  = "Order status updated successfully";
}
