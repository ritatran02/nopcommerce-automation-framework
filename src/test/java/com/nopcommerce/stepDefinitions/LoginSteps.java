package com.nopcommerce.stepDefinitions;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserLoginPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.PropertiesConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private PropertiesConfig propertiesConfig;

    @Before
    public void setUp() {
        propertiesConfig = PropertiesConfig.getProperties("env-dev");
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), "firefox");
        homePage = PageGenerator.getUserHomePage(driver);
    }

    @After
    public void tearDown() {
        closeBrowserDriver();
    }

    @Given("user is on the Home page")
    public void userIsOnTheHomePage() {
        Assert.assertTrue(driver.getCurrentUrl() != null);
    }

    @When("user clicks on Login link")
    public void userClicksOnLoginLink() {
        loginPage = homePage.clickToLoginLink();
    }

    @And("user enters email {string}")
    public void userEntersEmail(String email) {
        loginPage.enterToEmailTextbox(email);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterToPasswordTextbox(password);
    }

    @And("user clicks Login button")
    public void userClicksLoginButton() {
        loginPage.clickToLoginButton();
        homePage = PageGenerator.getUserHomePage(driver);
    }

    @Then("user should be redirected to Home page")
    public void userShouldBeRedirectedToHomePage() {
        Assert.assertTrue(driver.getCurrentUrl() != null);
    }

    @And("My Account link should be displayed")
    public void myAccountLinkShouldBeDisplayed() {
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Then("login error message should be displayed")
    public void loginErrorMessageShouldBeDisplayed() {
        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertFalse(errorMsg.isEmpty(), "Expected error message but got empty string");
    }
}
