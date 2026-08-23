package com.nopcommerce.pageObjects.user;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.pageUIs.user.UserLoginPageUI;
import org.openqa.selenium.WebDriver;

public class UserLoginPO extends BasePage {
    private WebDriver driver;

    public UserLoginPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX,emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
    }

    public UserHomePO loginToSystem(String emailAddress, String password){
        enterToEmailTextbox(emailAddress);
        enterToPasswordTextbox(password);
        clickToLoginButton();
        return PageGenerator.getUserHomePage(driver);
    }

    public String getLoginErrorMessage(){
        waitForElementVisible(driver,UserLoginPageUI.LOGIN_ERROR_MESSAGE);
        return getElementText(driver,UserLoginPageUI.LOGIN_ERROR_MESSAGE);
    }
}
