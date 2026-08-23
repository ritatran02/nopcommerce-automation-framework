package com.nopcommerce.pageUIs.user;

public class UserSearchPageUI {
    public static final String PRODUCT_NAME_TEXT = "class=product-item";
    public static final String SORT_DROPDOWN = "id=products-orderby";
    public static final String PRODUCT_PRICE = "class=price";
    public static final String NEXT_PAGE_BUTTON = "class=next-page";
    public static final String DYNAMIC_ADD_TO_CART_BUTTON = "xpath=//a[text()='%s']/ancestor::div[@class='details']//button[contains(@class,'add-to-cart')]";
    public static final String SHOPPING_CART_BUTTON = "xpath=//span[text()='Shopping cart']";
    public static final String ADD_TO_CART_SUCCESS_MESSAGE = "class=bar-notification";
    public static final String NOTIFICATION_CLOSE_BUTTON = "xpath=//div[@class='bar-notification']//span[@class='close']";
}
