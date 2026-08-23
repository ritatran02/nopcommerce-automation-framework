package com.nopcommerce.pageUIs;

import com.nopcommerce.pageObjects.admin.AdminHomePO;
import com.nopcommerce.pageObjects.user.*;
import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static UserHomePO getUserHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }

    public static UserLoginPO getUserLoginPage(WebDriver driver){
        return new UserLoginPO(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver){
        return new UserRegisterPO(driver);
    }


    public static UserSearchPO getUserSearchPage(WebDriver driver) {
        return new UserSearchPO(driver);
    }

    public static UserShoppingCartPO getUserShoppingCartPage(WebDriver driver){
        return new UserShoppingCartPO(driver);
    }

    public static AdminHomePO getAdminHomePage(WebDriver driver){
        return new AdminHomePO(driver);
    }
}