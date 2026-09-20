package mobile.brand;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import mobile.category.scroll.PageScroller;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;

import java.util.ArrayList;
import java.util.List;

@TestScoped
public class BrandManager {

    private final BrandFinder brandFinder;
    private final BrandCollector brandCollector;
    private final BrandNavigator brandNavigator;
    private final PageScroller pageScroller;
    private final AppiumDriver driver;

    @Inject
    public BrandManager(BrandFinder brandFinder,
                        BrandCollector brandCollector,
                        BrandNavigator brandNavigator,
                        PageScroller pageScroller,
                        AppiumDriver driver) {
        this.brandFinder = brandFinder;
        this.brandCollector = brandCollector;
        this.brandNavigator = brandNavigator;
        this.pageScroller = pageScroller;
        this.driver = driver;
    }

    public boolean findBrandDropdown() {
        return brandFinder.findBrandDropdown();
    }

    public List<String> clickBrand(boolean found) {
        List<String> brandlist = new ArrayList<>();
        if (found) {
            WebElement branddropdown = pageScroller.scrollToField("ბრენდი *");
            if (branddropdown != null) {
                branddropdown.click();
                brandlist = brandCollector.collectBrandsFromDropdown();
                for (String name : brandlist) {
                    System.out.println(name);
                }
                driver.navigate().back();
                driver.navigate().back();
                pageScroller.goBackToCategories();
            } else {
                brandNavigator.returnToForm(true);
            }
        }
        return brandlist;
    }
}