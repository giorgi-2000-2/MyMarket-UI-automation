package org.example.pages.basepage;

import org.example.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
    private final Waits waitUtils;

    public WaitHelper(Waits waitUtils) {
        this.waitUtils = waitUtils;
    }

    public WebElement waitElementToBeClickable(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitElementToBeClickable(WebDriverWait wait, WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitElementToBeVisible(WebDriverWait wait, WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void waitString(WebElement element) {
        waitUtils.getTextWait().until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, "აირჩიე/ჩაწერე კატეგორია")
        ));
    }
}