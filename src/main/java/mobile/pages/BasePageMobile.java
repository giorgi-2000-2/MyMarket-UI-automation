package mobile.pages;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestScoped
public class BasePageMobile {
    protected final Waits waits;
@Inject
    protected BasePageMobile( Waits waits) {
        this.waits = waits;
    }

    protected void visible(WebElement el) {
        waitElementToBeVisible(waits.getWait(), el);
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