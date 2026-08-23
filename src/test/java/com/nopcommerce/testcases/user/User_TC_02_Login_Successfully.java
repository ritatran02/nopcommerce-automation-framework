package com.nopcommerce.testcases.user;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserLoginPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_TC_02_Login_Successfully extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private PropertiesConfig propertiesConfig;
    private LoginData login;

    @Parameters({"server","browser"})
    @BeforeClass
    public void beforeClass(String server, String browserName){
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);
        homePage = PageGenerator.getUserHomePage(driver);
        TestData testData = TestData.getLoginData();
        login = testData.getUserLogin();
    }

    @Test(groups = "jenkins")
    public void User_TC_02_Login() {
        loginPage = homePage.clickToLoginLink();
        homePage = loginPage.loginToSystem(login.getEmailAddress(), propertiesConfig.getApplicationPassword());
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
