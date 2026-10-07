package web.pages.basepage;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import org.openqa.selenium.StaleElementReferenceException;
import uicommon.utils.Waits;
import uicommon.driver.IDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestScoped
public class PageAction {
    private final Waits waitUtils;
    private final IDriver driver;
    private final JavaScriptHelper javaScriptHelper;
@Inject
    public PageAction(Waits waitUtils, IDriver driver, JavaScriptHelper javaScriptHelper) {
        this.waitUtils = waitUtils;
    this.driver = driver;
    this.javaScriptHelper = javaScriptHelper;
}


    public String getCurrentURL() {
        return driver.getDriver().getCurrentUrl();
    }
    public void sendKeys(WebElement locator, String text) {
        waitElementToBeVisible(waitUtils.getWait(), locator);
        locator.clear();
        locator.sendKeys(text);
    }
    public void waitClick(WebElement element) {
        javaScriptHelper.scroll(element);
        waitElementToBeVisible(waitUtils.getShortWait(), element);
        waitUtils.getShortWait().until(d -> javaScriptHelper.isElementInViewport(element));

        try {
            waitUtils.getShortWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e) {
            throw e;
        }
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
