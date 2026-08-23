package com.nopcommerce.testcases.user;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserSearchPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.PropertiesConfig;
import com.nopcommerce.utilities.SearchData;
import com.nopcommerce.utilities.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class User_TC_05_Sort_Product_By_Price extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserSearchPO searchPage;
    private PropertiesConfig propertiesConfig;
    private SearchData search;

    @Parameters({"server","browser"})
    @BeforeClass
    public void beforeClass(String server, String browserName){
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);
        homePage = PageGenerator.getUserHomePage(driver);
        TestData testData = TestData.getSearchData();
        search = testData.getSearch();
    }

    @Test(groups = "jenkins")
    public void TC_05_Sort_Product_By_Price() throws ParseException {
        searchPage = homePage.searchProduct(search.getSearch());
        List<String> productNameList = searchPage.getProductNameList();

        searchPage.sortBy("Price: Low to High");
        verifyEquals(searchPage.getSortItemSelected(),"Price: Low to High");
        searchPage.sleepInSeconds(5);
        verifyTrue(searchPage.isProductPriceSortByAscending());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
