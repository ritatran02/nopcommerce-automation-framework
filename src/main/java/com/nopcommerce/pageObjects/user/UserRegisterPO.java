package com.nopcommerce.pageObjects.user;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.pageUIs.user.UserRegisterPageUI;
import org.openqa.selenium.WebDriver;

public class UserRegisterPO extends BasePage {
    private WebDriver driver;

    public UserRegisterPO(WebDriver driver){
        this.driver = driver;
    }

//    @Step("Click to Female Ratio")
    public void clickToFemaleRatio() {
        waitForElementClickable(driver, UserRegisterPageUI.GENDER_FEMALE_RADIO);
        checkToCheckboxRadio(driver, UserRegisterPageUI.GENDER_FEMALE_RADIO);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectItemInDropdown(driver, UserRegisterPageUI.DAY_DROPDOWN, day);
        selectItemInDropdown(driver, UserRegisterPageUI.MONTH_DROPDOWN, month);
        selectItemInDropdown(driver, UserRegisterPageUI.YEAR_DROPDOWN, year);
    }

    public void enterToCompanyNameTextbox(String companyName) {
        waitForElementVisible(driver, UserRegisterPageUI.COMPANY_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.COMPANY_TEXTBOX, companyName);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public UserLoginPO clickToLoginLink() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGIN_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGIN_LINK);
        return PageGenerator.getUserLoginPage(driver);
    }

    public UserHomePO clickToLogoutLink() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
        return PageGenerator.getUserHomePage(driver);
    }
}

