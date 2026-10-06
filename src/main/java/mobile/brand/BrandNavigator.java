package mobile.brand;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import mobile.category.scroll.IPageScroller;
import core.annotations.TestScoped;

@TestScoped
public class BrandNavigator {

    private final AppiumDriver driver;
    private final IPageScroller IPageScroller;

    @Inject
    public BrandNavigator(AppiumDriver driver, IPageScroller IPageScroller) {
        this.driver = driver;
        this.IPageScroller = IPageScroller;
    }

    public void returnToForm(boolean characteristicsOpened) {
        try {
            if (characteristicsOpened) {
                driver.navigate().back();
            }
            IPageScroller.goBackToCategories();
        } catch (Exception ignored) {
        }
    }

    public void backToCategories(){
        driver.navigate().back();
        driver.navigate().back();
        IPageScroller.goBackToCategories();
    }
}