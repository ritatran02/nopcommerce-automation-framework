package com.nopcommerce.pageObjects.admin;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.admin.AdminEditPageUI;
import com.nopcommerce.pageUIs.admin.AdminProductPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminEditPO extends BasePage {
    private WebDriver driver;

    public AdminEditPO(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadProductImage(String imagePath) {
        getElement(driver, AdminEditPageUI.UPLOAD_FILE_INPUT).sendKeys(imagePath);
        sleepInSeconds(3);
    }

    public AdminProductPO editSKU(String SKU) {
        waitForElementVisible(driver, AdminEditPageUI.SKU);
        sendKeyToElement(driver, AdminEditPageUI.SKU, SKU);
        waitForElementClickable(driver, AdminEditPageUI.SAVE_BUTTON);
        clickToElement(driver, AdminEditPageUI.SAVE_BUTTON);

        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.urlContains("/Admin/Product/List"));
        } catch (Exception e) {
            String baseUrl = driver.getCurrentUrl().split("/Admin")[0];
            driver.get(baseUrl + "/Admin/Product/List");
        }

        waitForElementVisible(driver, AdminProductPageUI.PRODUCT_NAME);
        return new AdminProductPO(driver);
    }
}
