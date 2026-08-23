package com.nopcommerce.pageObjects.user;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageObjects.admin.AdminHomePO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.pageUIs.user.UserHomePageUI;
import org.openqa.selenium.WebDriver;

public class UserHomePO extends BasePage {
    private WebDriver driver;
    public UserHomePO(WebDriver driver){
        this.driver = driver;
    }

    public UserRegisterPO clickToRegisterLink() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getUserRegisterPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }


    public UserLoginPO clickToLoginLink() {
        waitForElementVisible(driver, UserHomePageUI.LOGIN_BUTTON);
        clickToElement(driver,UserHomePageUI.LOGIN_BUTTON);
        return new UserLoginPO(driver);
    }

    public UserSearchPO searchProduct(String searchData){
        waitForElementClickable(driver, UserHomePageUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, searchData);
        clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
        return PageGenerator.getUserSearchPage(driver);

    }

    public AdminHomePO clickToAdminHome(){
        waitForElementClickable(driver, UserHomePageUI.ADMIN_BUTTON);
        clickToElement(driver, UserHomePageUI.ADMIN_BUTTON);
        return PageGenerator.getAdminHomePage(driver);
    }
}

