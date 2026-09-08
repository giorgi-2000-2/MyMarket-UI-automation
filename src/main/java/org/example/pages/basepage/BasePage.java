package org.example.pages.basepage;

import lombok.Getter;
import org.example.utils.Waits;
import org.example.utils.reporter.stringutils.StringSplitter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final WebDriver driver;
    protected final Waits waitUtils;
    protected final StringSplitter stringSplitter;
@Getter
    protected final WaitHelper waitHelper;
@Getter
    protected final JavaScriptHelper jsHelper;


    public BasePage(WebDriver driver, Waits waitUtils, StringSplitter stringSplitter) {
        this.driver = driver;
        this.waitUtils = waitUtils;
        this.stringSplitter = stringSplitter;

        this.waitHelper = new WaitHelper(waitUtils);
        this.jsHelper = new JavaScriptHelper(driver);
        PageFactory.initElements(driver, this);
    }


    public void sendKeys(WebElement locator, String text) {
        waitHelper.waitElementToBeVisible(waitUtils.getWait(), locator);
        locator.clear();
        locator.sendKeys(text);
    }

    public void click(WebDriverWait wait, By locator) {
        waitHelper.waitElementToBeClickable(wait, locator).click();
    }

    public void click(WebElement locator) {
        waitHelper.waitElementToBeClickable(waitUtils.getWait(), locator);
        locator.click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void scroll(WebElement element) {
        jsHelper.scroll(element);
    }


    public void waitClick(WebElement element) {
        scroll(element);
        waitHelper.waitElementToBeVisible(waitUtils.getShortWait(), element);
        waitUtils.getShortWait().until(d -> jsHelper.isElementInViewport(element));

        try {
            waitUtils.getShortWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e) {
            throw e;
        }
    }

    public String titleText(WebElement locator) {
        String titleTxt = locator.getText();
        try {
            waitUtils.getTextWait().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, titleTxt)));
            return stringSplitter.getSplitString(locator.getText());
        } catch (Exception e) {
            return stringSplitter.getSplitString(locator.getText());
        }
    }
}