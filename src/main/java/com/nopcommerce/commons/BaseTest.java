package com.nopcommerce.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;

public class BaseTest {
        public static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();

        public static WebDriver getDriver(){
            return ThreadDriver.get();
        }

        protected WebDriver getBrowserDriver(String url, String browserName){
            BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
            switch (browserList) {
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-private");
                    ThreadDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--incognito");
                    ThreadDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    ThreadDriver.set(new EdgeDriver(edgeOptions));
                    break;
                case HEAD_FIREFOX:
                    FirefoxOptions headFirefoxOptions = new FirefoxOptions();
                    headFirefoxOptions.addArguments("--headless");
                    headFirefoxOptions.addArguments("window-size=2064x1120");
                    ThreadDriver.set(new FirefoxDriver(headFirefoxOptions));
                    break;
                case HEAD_EDGE:
                    EdgeOptions headEdgeOptions = new EdgeOptions();
                    headEdgeOptions.addArguments("--headless");
                    headEdgeOptions.addArguments("window-size=2064x1120");
                    ThreadDriver.set(new EdgeDriver(headEdgeOptions));
                    break;
                case HEAD_CHROME:
                    ChromeOptions headChromeOptions = new ChromeOptions();
                    headChromeOptions.addArguments("--headless");
                    headChromeOptions.addArguments("--window-size=2064x1120");
                    ThreadDriver.set(new ChromeDriver(headChromeOptions));
                    break;
                default:
                    throw new RuntimeException("Browser name is not valid!");
            }
            ThreadDriver.get().get(url);
            ThreadDriver.get().manage().window().maximize();
            ThreadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            return ThreadDriver.get();
        }


        protected boolean verifyTrue(boolean condition){
            boolean pass = true;
            try {
                Assert.assertTrue(condition);
            } catch (Throwable e){
                pass = false;
                VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
                Reporter.getCurrentTestResult().setThrowable(e);
            }
            return pass;
        }

        protected boolean verifyEquals (Object actual, Object excepted){
            boolean pass = true;
            try {
                Assert.assertEquals(actual, excepted);
            } catch (Throwable e){
                pass = false;
                VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(),e);
                Reporter.getCurrentTestResult().setThrowable(e);
            }
            return pass;
        }

    @BeforeSuite
    public void deleteFileInReport() {
        deleteAllFileInFolder("htmlAllure");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles != null && listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void closeBrowserDriver() {
        try {
            WebDriver currentDriver = ThreadDriver.get();

            if (currentDriver != null) {
                currentDriver.manage().deleteAllCookies();
                currentDriver.quit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ThreadDriver.remove();
        }
    }
}
