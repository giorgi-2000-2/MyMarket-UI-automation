package mobile.category.navigation;

import com.google.inject.Inject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobile.category.model.Item;
import mobile.category.model.Snapshot;
import mobile.category.screen.ScreenReader;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryItemClicker {
    private final AppiumDriver driver;
    private final ScreenReader screenReader;
    private final CategoryItemFinder itemFinder;
    private final ComfortZoneAligner comfortZoneAligner;

    @Inject
    public CategoryItemClicker(AppiumDriver driver, ScreenReader screenReader,
                               CategoryItemFinder itemFinder, ComfortZoneAligner comfortZoneAligner) {
        this.driver = driver;
        this.screenReader = screenReader;
        this.itemFinder = itemFinder;
        this.comfortZoneAligner = comfortZoneAligner;
    }

    public Snapshot clickByName(String name) {
        Item target = itemFinder.findWithScroll(name);

        target = comfortZoneAligner.bringIntoComfortZone(target);

        List<WebElement> found = driver.findElements(AppiumBy.accessibilityId(target.raw));

        Snapshot atTap = screenReader.read();
        found.get(0).click();
        return atTap;
    }
}
