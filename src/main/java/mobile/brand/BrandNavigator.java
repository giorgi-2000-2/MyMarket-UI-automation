package mobile.brand;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import mobile.category.scroll.PageScroller;
import core.annotations.TestScoped;

@TestScoped
public class BrandNavigator {

    private final AppiumDriver driver;
    private final PageScroller pageScroller;

    @Inject
    public BrandNavigator(AppiumDriver driver, PageScroller pageScroller) {
        this.driver = driver;
        this.pageScroller = pageScroller;
    }

    public void returnToForm(boolean characteristicsOpened) {
        try {
            if (characteristicsOpened) {
                driver.navigate().back();
            }
            pageScroller.goBackToCategories();
        } catch (Exception ignored) {
        }
    }
}