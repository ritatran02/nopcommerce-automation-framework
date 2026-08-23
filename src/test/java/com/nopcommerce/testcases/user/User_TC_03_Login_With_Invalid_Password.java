package com.nopcommerce.testcases.user;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.jiraConfigs.JiraCreateIssue;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserLoginPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.LoginData;
import com.nopcommerce.utilities.PropertiesConfig;
import com.nopcommerce.utilities.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_TC_03_Login_With_Invalid_Password extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private PropertiesConfig propertiesConfig;
    private LoginData failedLogin;

    @Parameters({"server","browser"})
    @BeforeClass
    public void beforeClass(String server, String browserName){
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);
        homePage = PageGenerator.getUserHomePage(driver);
        TestData testData = TestData.getLoginData();
        failedLogin = testData.getFailedLogin();
    }

    @Description("User_TC_03_Login_With_Invalid_Password")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @JiraCreateIssue(isCreateIssue = true)
    public void User_TC_03_Login_With_Invalid_Password() {
        loginPage = homePage.clickToLoginLink();
        homePage = loginPage.loginToSystem(failedLogin.getEmailAddress(), failedLogin.getPassword());
        Assert.assertEquals(loginPage.getLoginErrorMessage(),"");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
