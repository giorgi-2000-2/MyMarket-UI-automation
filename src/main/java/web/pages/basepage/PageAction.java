package web.pages.basepage;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.config.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
@TestScoped
public class ElementAction {
    private final Waits waitUtils;
@Inject
    public ElementAction(Waits waitUtils) {
        this.waitUtils = waitUtils;
    }


    public void sendKeys(WebElement locator, String text) {
        waitElementToBeVisible(waitUtils.getWait(), locator);
        locator.clear();
        locator.sendKeys(text);
    }

    public void click(WebDriverWait wait, By locator) {
        waitElementToBeClickable(wait, locator).click();
    }

    public void click(WebElement locator) {
        waitElementToBeClickable(waitUtils.getWait(), locator);
        locator.click();
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
}
