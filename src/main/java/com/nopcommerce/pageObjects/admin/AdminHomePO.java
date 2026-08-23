package com.nopcommerce.pageObjects.admin;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.admin.AdminHomePageUI;
import org.openqa.selenium.WebDriver;

public class AdminHomePO extends BasePage {
    private WebDriver driver;
    public AdminHomePO(WebDriver driver){
        this.driver = driver;
    }

    public void clickToCatalog(){
        waitForElementClickable(driver, AdminHomePageUI.CATALOG);
        if (!isElementDisplayed(driver, AdminHomePageUI.PRODUCTS)) {
            clickToElement(driver, AdminHomePageUI.CATALOG);
            waitForElementVisible(driver, AdminHomePageUI.PRODUCTS);
        }
    }

    public AdminProductPO clickToProduct(){
        waitForElementClickable(driver, AdminHomePageUI.PRODUCTS);
        clickToElement(driver, AdminHomePageUI.PRODUCTS);
        return new AdminProductPO(driver);
    }
}
