package com.nopcommerce.testcases.user;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserLoginPO;
import com.nopcommerce.pageObjects.user.UserSearchPO;
import com.nopcommerce.pageObjects.user.UserShoppingCartPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.LoginData;
import com.nopcommerce.utilities.PropertiesConfig;
import com.nopcommerce.utilities.SearchData;
import com.nopcommerce.utilities.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_TC_06_Add_Product_To_Cart_Flow extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private PropertiesConfig propertiesConfig;
    private LoginData login;
    private SearchData search;
    private UserSearchPO searchPage;
    private UserShoppingCartPO shoppingCartPage;

    @Parameters({"server","browser"})
    @BeforeClass
    public void beforeClass(String server, String browserName){
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);
        homePage = PageGenerator.getUserHomePage(driver);
        TestData testData = TestData.getLoginData();
        login = testData.getUserLogin();
        TestData testSearchData = TestData.getSearchData();
        search = testSearchData.getSearch();
    }

    @Test(groups = "jenkins")
    public void TC_06_Add_Product_To_Cart_Flow() {
        loginPage = homePage.clickToLoginLink();
        homePage = loginPage.loginToSystem(login.getEmailAddress(), propertiesConfig.getApplicationPassword());

        searchPage = homePage.searchProduct(search.getSearch());
        searchPage.addProductToCart(search.getProductName());
        Assert.assertTrue(searchPage.getAddToCartSuccessessage().contains("The product has been added to your shopping cart"),
                "Add to Cart success message is incorrect");

        shoppingCartPage = searchPage.clickToShoppingCartLink();
        Assert.assertTrue(shoppingCartPage.isProductInCart(search.getProductName(), search.getPrice()));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
