package com.nopcommerce.testcases.z_admin;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.GlobalConstants;
import com.nopcommerce.pageObjects.admin.AdminEditPO;
import com.nopcommerce.pageObjects.admin.AdminHomePO;
import com.nopcommerce.pageObjects.admin.AdminProductPO;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserLoginPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class Admin_TC_07_Edit_Product_Successfully extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private PropertiesConfig propertiesConfig;
    private LoginData login;
    private AdminHomePO adminHomePage;
    private AdminProductPO adminProductPage;
    private SearchData search;
    private String searchKeyword;
    private AdminEditPO adminEditPage;
    private EditData edit;
    private String imagePath;

    @Parameters({"server", "browser"})
    @BeforeMethod
    public void beforeMethod(String server, String browserName) {
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);
        homePage = PageGenerator.getUserHomePage(driver);
        TestData testData = TestData.getLoginData();
        login = testData.getLogin();
        TestData testSearchData = TestData.getSearchData();
        search = testSearchData.getSearch();
        searchKeyword = search.getSearch();
        TestData testEditData = TestData.getEditData();
        edit = testEditData.getSKU();
        imagePath = GlobalConstants.UPLOAD_PATH + "Apple MacBook Pro 14-inch.jpg";
    }

    //@Test
    public void Admin_TC_07_Edit_Product_Successfully() {
        loginPage = homePage.clickToLoginLink();
        homePage = loginPage.loginToSystem(login.getEmailAddress(), propertiesConfig.getApplicationPassword());

        adminHomePage = homePage.clickToAdminHome();
        adminHomePage.clickToCatalog();
        adminProductPage = adminHomePage.clickToProduct();

        adminProductPage.searchProduct(searchKeyword);
        List<String> actualProductNameList = adminProductPage.getAllProductNameList();

        for (String productName : actualProductNameList) {
            verifyTrue(
                    productName.toLowerCase().contains(searchKeyword.toLowerCase())
            );
        }

        adminEditPage = adminProductPage.clickToEditButton(search.getProductName());
        adminEditPage.uploadProductImage(imagePath);
        adminProductPage = adminEditPage.editSKU(edit.getSKU());

        adminProductPage.searchProduct(searchKeyword);

        String expectedSKU = edit.getSKU();
        String actualSKU = adminProductPage.getProductSKU(search.getProductName());
        verifyTrue(expectedSKU.equals(actualSKU));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        closeBrowserDriver();
    }
}
