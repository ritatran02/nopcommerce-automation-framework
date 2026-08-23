package com.nopcommerce.pageObjects.user;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.pageUIs.user.UserSearchPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserSearchPO extends BasePage {
    private WebDriver driver;
    public UserSearchPO(WebDriver driver){
        this.driver = driver;
    }

    public List<String> getProductNameList() {
        return getElementsText(driver, UserSearchPageUI.PRODUCT_NAME_TEXT);
    }

    public List<String> getAllProductNameList() {
        List<String> allProductNameList = new ArrayList<>();

        while (isElementDisplayed(driver, UserSearchPageUI.NEXT_PAGE_BUTTON)) {

            allProductNameList.addAll(getProductNameList());

            clickNextPage(driver, UserSearchPageUI.NEXT_PAGE_BUTTON);
            sleepInSeconds(2);
        }

        allProductNameList.addAll(getProductNameList());

        return allProductNameList;
    }

    public void sortBy(String sortCriteria) {
        waitForElementClickable(driver, UserSearchPageUI.SORT_DROPDOWN);
        selectItemInDropdown(driver, UserSearchPageUI.SORT_DROPDOWN, sortCriteria);
    }

    public String getSortItemSelected(){
        waitForElementVisible(driver, UserSearchPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(driver, UserSearchPageUI.SORT_DROPDOWN);
    }

    public List<Float> getAllProductPriceList() {
        List<Float> allProductPriceList = new ArrayList<>();

        while (isElementDisplayed(driver, UserSearchPageUI.NEXT_PAGE_BUTTON)) {
            allProductPriceList.addAll(getProductPriceList());

            clickNextPage(driver, UserSearchPageUI.NEXT_PAGE_BUTTON);

            sleepInSeconds(2);
        }

        allProductPriceList.addAll(getProductPriceList());
        return allProductPriceList;
    }

    public List<Float> getProductPriceList() {
        List<Float> productPriceList = new ArrayList<>();

        List<WebElement> productPrices =
                getListElement(driver, UserSearchPageUI.PRODUCT_PRICE);

        for (WebElement productPrice : productPrices) {
            productPriceList.add(
                    Float.parseFloat(
                            productPrice.getText()
                                    .replace("$", "")
                                    .replace(",", "")
                    )
            );
        }

        return productPriceList;
    }

    public boolean isProductPriceSortByAscending() {
        List<Float> productPriceList = getAllProductPriceList();

        List<Float> sortedList = new ArrayList<>(productPriceList);
        Collections.sort(sortedList);

        return productPriceList.equals(sortedList);
    }

    public void addProductToCart(String productName) {
        waitForElementClickable(driver,UserSearchPageUI.DYNAMIC_ADD_TO_CART_BUTTON,productName);
        clickToElement(driver, UserSearchPageUI.DYNAMIC_ADD_TO_CART_BUTTON, productName);
    }

    public UserShoppingCartPO clickToShoppingCartLink() {
        if (isElementDisplayed(driver, UserSearchPageUI.NOTIFICATION_CLOSE_BUTTON)) {
            clickToElement(driver, UserSearchPageUI.NOTIFICATION_CLOSE_BUTTON);
            sleepInSeconds(1);
        }
        waitForElementClickable(driver, UserSearchPageUI.SHOPPING_CART_BUTTON);
        clickToElement(driver, UserSearchPageUI.SHOPPING_CART_BUTTON);
        return PageGenerator.getUserShoppingCartPage(driver);
    }

    public String getAddToCartSuccessessage(){
        waitForElementVisible(driver, UserSearchPageUI.ADD_TO_CART_SUCCESS_MESSAGE);
        return getElementText(driver, UserSearchPageUI.ADD_TO_CART_SUCCESS_MESSAGE);
    }
}
