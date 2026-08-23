package com.nopcommerce.pageObjects.user;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.user.UserShoppingCartPageUI;
import org.openqa.selenium.WebDriver;

public class UserShoppingCartPO extends BasePage {
    private WebDriver driver;
    public UserShoppingCartPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isProductInCart(String productName, String price) {

        String productLocator = String.format(
                UserShoppingCartPageUI.DYNAMIC_PRODUCT_NAME,
                productName
        );

        String priceLocator = String.format(
                UserShoppingCartPageUI.DYNAMIC_PRODUCT_PRICE,
                productName
        );

        String actualProduct = getElement(driver, productLocator).getText();
        String actualPriceText = getElement(driver, priceLocator).getText();

        float actualPrice = Float.parseFloat(
                actualPriceText.replace("$", "").replace(",", "")
        );

        float expectedPrice = Float.parseFloat(
                price.replace("$", "").replace(",", "")
        );

        return actualProduct.equals(productName)
                && actualPrice == expectedPrice;
    }
}
