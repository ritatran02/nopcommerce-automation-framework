package com.nopcommerce.pageUIs.admin;

public class AdminProductPageUI {
    public static final String PRODUCT_NAME = "id=SearchProductName";
    public static final String SEARCH_BUTTON = "id=search-products";
    public static final String NEXT_PAGE_BUTTON = "xpath=//a[text()='Next']";
    public static final String PRODUCT_TABLE_BODY = "xpath=//table[@id='products-grid']//tbody/tr";
    public static final String DYNAMIC_PRODUCT_NAME = "xpath=//tbody/tr[td[3][text()='%s']]/td[3]";
    public static final String DYNAMIC_EDIT_BUTTON = "xpath=//tbody/tr[td[3][text()='%s']]/td[8]";
    public static final String DYNAMIC_SKU = "xpath=//tbody/tr[td[3][text()='%s']]/td[4]";
}

