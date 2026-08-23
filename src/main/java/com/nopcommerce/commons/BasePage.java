package com.nopcommerce.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

public class BasePage {
    public By getLocator(String prefixLocator){
        By by;
        String locator = prefixLocator.toUpperCase();
        if(locator.startsWith("ID")){
            by = By.id(prefixLocator.substring(3));
        } else  if (locator.startsWith("CLASS")){
            by = By.className(prefixLocator.substring(6));
        } else if (locator.startsWith("NAME")){
            by = By.name(prefixLocator.substring(5));
        } else if (locator.startsWith("TAGNAME")){
            by = By.tagName(prefixLocator.substring(8));
        } else if (locator.startsWith("CSS")){
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (locator.startsWith("XPATH")){
            by = By.xpath(prefixLocator.substring(6));
        } else  {
            throw new RuntimeException("Locator type is not support!");
        }
        return by;
    }

    public String castParameter(String locator, String... restParameter){
        return String.format(locator, (Object[]) restParameter);
    }

    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getLocator(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
        getElement(driver,locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter){
        getElement(driver,castParameter(locator,restParameter)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String key){
        getElement(driver,locator).clear();
        getElement(driver,locator).sendKeys(key);
    }

    public String getElementText(WebDriver driver, String locator){
        return getElement(driver,locator).getText();
    }

    public List<String> getElementsText(WebDriver driver, String locator) {
        List<WebElement> elements = getListElement(driver, locator);
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getLocator(castParameter(locator,restParameter))));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... restParameter){
        new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getLocator(castParameter(locator,restParameter))));
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return getElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator){
        if (!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    public void clickNextPage(WebDriver driver, String nextButtonLocator) {
        waitForElementClickable(driver, nextButtonLocator);
        clickToElement(driver, nextButtonLocator);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem){
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
