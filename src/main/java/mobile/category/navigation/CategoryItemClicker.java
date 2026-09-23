package mobile.category.navigation;

import com.google.inject.Inject;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import core.reporter.ReportStatus;
import core.reporter.extentreport.RetryReporter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobile.category.model.Item;
import mobile.category.model.Snapshot;
import mobile.category.screen.ScreenReader;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class CategoryItemClicker {
    private final AppiumDriver driver;
    private final ScreenReader screenReader;
    private final CategoryItemFinder itemFinder;
    private final ComfortZoneAligner comfortZoneAligner;
    private final RetryReporter retryReporter;
    @Inject
    public CategoryItemClicker(AppiumDriver driver, ScreenReader screenReader,
                               CategoryItemFinder itemFinder, ComfortZoneAligner comfortZoneAligner , RetryReporter retryReporter) {
        this.driver = driver;
        this.screenReader = screenReader;
        this.itemFinder = itemFinder;
        this.comfortZoneAligner = comfortZoneAligner;
        this.retryReporter = retryReporter;
    }

    public Snapshot clickByName(String name) {
        int maxRetries = 3;
        Exception last = null;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Item target = itemFinder.findWithScroll(name);
                target = comfortZoneAligner.bringIntoComfortZone(target);

                List<WebElement> found = driver.findElements(
                        AppiumBy.accessibilityId(target.raw));

                if (found.isEmpty()) {
                    throw new NoSuchElementException("ვერ მოიძებნა: " + name);
                }

                Snapshot atTap = screenReader.read();
                found.get(0).click();
                return atTap;

            } catch (StaleElementReferenceException
                     | IndexOutOfBoundsException
                     | NoSuchElementException e) {
                last = e;
                retryReporter.logAttempt("clickByName", name, attempt, e);
            }
        }

        throw new RuntimeException(
                "კლიკი ვერ მოხერხდა " + maxRetries + " ცდის შემდეგ: " + name, last);
    }
}
