package com.nopcommerce.testcases.user;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.pageObjects.user.UserHomePO;
import com.nopcommerce.pageObjects.user.UserRegisterPO;
import com.nopcommerce.pageUIs.PageGenerator;
import com.nopcommerce.utilities.*;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;

public class User_TC_01_Register_Successfully extends BaseTest {
    private WebDriver driver;
    private UserRegisterPO registerPage;
    private UserHomePO homePage;
    private String firstName, lastName, emailAddress;
    private DataConfigNet dataConfigNet;
    private PropertiesConfig propertiesConfig;
    private String dbEmail, dbFirstName, dbLastName, dbCompanyName, dbGender;
    private Date dbDateOfBirth;
    private RegisterData register;

    @Parameters({"server","browser"})
    @BeforeClass
    public void beforeClass(String server, String browserName){
        propertiesConfig = PropertiesConfig.getProperties(server);
        driver = getBrowserDriver(propertiesConfig.getApplicationUrl(), browserName);
        homePage = PageGenerator.getUserHomePage(driver);
        dataConfigNet = DataConfigNet.getData();
        firstName = dataConfigNet.getFirstName();
        lastName = dataConfigNet.getLastName();
        emailAddress = dataConfigNet.getEmailAddress();
        TestData testData = TestData.getRegisterData();
        register = testData.getRegister();
    }

    @Description("User_TC_01_Register")
    @Test

    public void User_TC_01_Register() throws SQLException {
        registerPage = homePage.clickToRegisterLink();
        registerPage.clickToFemaleRatio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.selectDateOfBirth(register.getDay(),register.getMonth(),register.getYear());
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyNameTextbox(register.getCompany());
        registerPage.enterToPasswordTextbox(propertiesConfig.getApplicationPassword());
        registerPage.enterToConfirmPasswordTextbox(propertiesConfig.getApplicationPassword());
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

        Connection connection = SQLServerConfig.getSQLServerConnection();

        try {
            String query = "SELECT Email, FirstName, LastName, Gender, Company, DateOfBirth " +
                    "FROM Customer WHERE Email = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAddress);

            ResultSet result = statement.executeQuery();

            Assert.assertTrue(result.next(), "Customer was not found in DB");

            dbEmail = result.getString("Email");
            dbFirstName = result.getString("FirstName");
            dbLastName = result.getString("LastName");
            dbGender = result.getString("Gender");
            dbCompanyName = result.getString("Company");
            dbDateOfBirth = result.getDate("DateOfBirth");

            Assert.assertEquals(dbEmail, emailAddress);
            Assert.assertEquals(dbFirstName, firstName);
            Assert.assertEquals(dbLastName, lastName);
            Assert.assertEquals(dbCompanyName, register.getCompany());

            Assert.assertEquals(dbGender, register.getGender());

            int monthNumber = Month.valueOf(
                    register.getMonth().toUpperCase()
            ).getValue();

            Date expectedDateOfBirth = Date.valueOf(
                    String.format("%s-%02d-%02d",
                            register.getYear(),
                            monthNumber,
                            Integer.parseInt(register.getDay()))
            );
            Assert.assertEquals(dbDateOfBirth, expectedDateOfBirth);
        } finally {
            connection.close();
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
