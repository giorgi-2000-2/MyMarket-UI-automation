package mobile.category.navigation;

import com.google.inject.Inject;
import core.utils.RetryPolicy;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobile.category.model.Item;
import mobile.category.model.Snapshot;
import mobile.category.screen.IScreenReader;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryItemClicker {
    private final AppiumDriver driver;
    private final IScreenReader IScreenReader;
    private final CategoryItemFinder itemFinder;
    private final ComfortZoneAligner comfortZoneAligner;
    private final RetryPolicy retryPolicy;

    @Inject
    public CategoryItemClicker(AppiumDriver driver,
                               IScreenReader IScreenReader,
                               CategoryItemFinder itemFinder,
                               ComfortZoneAligner comfortZoneAligner,
                               RetryPolicy retryPolicy) {
        this.driver = driver;
        this.IScreenReader = IScreenReader;
        this.itemFinder = itemFinder;
        this.comfortZoneAligner = comfortZoneAligner;
        this.retryPolicy = retryPolicy;
    }

    public Snapshot clickByName(String name) {
        return retryPolicy.run(
                "clickByName",
                name,
                () -> tryClick(name),
                () -> { },
                e -> isRetryable(e));
    }

    private boolean isRetryable(RuntimeException e) {
        return e instanceof StaleElementReferenceException
                || e instanceof ElementClickInterceptedException
                || e instanceof IndexOutOfBoundsException;
    }

    private Snapshot tryClick(String name) {
        Item target = itemFinder.findWithScroll(name);
        target = comfortZoneAligner.bringIntoComfortZone(target);

        List<WebElement> found = driver.findElements(AppiumBy.accessibilityId(target.raw));
        if (found.isEmpty()) {
            throw new StaleElementReferenceException(
                    "ელემენტი გაქრა DOM-იდან სნეპშოტის წაკითხვის შემდეგ: " + target.raw);
        }

        Snapshot atTap = IScreenReader.read();
        found.get(0).click();
        return atTap;
    }
}