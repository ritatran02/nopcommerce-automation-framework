package com.nopcommerce.pageObjects.admin;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.admin.AdminProductPageUI;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class AdminProductPO extends BasePage {
    private WebDriver driver;
    public AdminProductPO(WebDriver driver){
        this.driver = driver;
    }

    public void searchProduct(String searchData){
        waitForElementClickable(driver, AdminProductPageUI.PRODUCT_NAME, searchData);
        sendKeyToElement(driver, AdminProductPageUI.PRODUCT_NAME, searchData);
        waitForElementClickable(driver, AdminProductPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminProductPageUI.SEARCH_BUTTON);
        sleepInSeconds(2);
        waitForElementVisible(driver, AdminProductPageUI.PRODUCT_TABLE_BODY);
    }

    public List<String> getProductNameList() {
        return getElementsText(driver, AdminProductPageUI.DYNAMIC_PRODUCT_NAME);
    }

    public List<String> getAllProductNameList() {
        List<String> allProductNameList = new ArrayList<>();

        while (true) {
            allProductNameList.addAll(getProductNameList());

            if (!isElementDisplayed(driver, AdminProductPageUI.NEXT_PAGE_BUTTON)) {
                break;
            }

            String ariaDisabled = getElement(
                    driver,
                    AdminProductPageUI.NEXT_PAGE_BUTTON
            ).getAttribute("aria-disabled");

            if ("true".equals(ariaDisabled)) {
                break;
            }

            clickNextPage(driver, AdminProductPageUI.NEXT_PAGE_BUTTON);
            sleepInSeconds(2);
        }

        return allProductNameList;
    }

    public AdminEditPO clickToEditButton(String searchData){
        waitForElementClickable(driver, AdminProductPageUI.DYNAMIC_EDIT_BUTTON,searchData);
        clickToElement(driver, AdminProductPageUI.DYNAMIC_EDIT_BUTTON, searchData);
        return new AdminEditPO(driver);
    }

    public String getProductSKU(String productName) {
        waitForElementVisible(driver, AdminProductPageUI.DYNAMIC_SKU,productName);
        String SKU = getElementText(
                driver,
                String.format(AdminProductPageUI.DYNAMIC_SKU, productName));
        return SKU.trim();
    }
}
