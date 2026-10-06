package mobile.brand;

import com.google.inject.Inject;
import mobile.category.scroll.IPageScroller;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;

import java.util.ArrayList;
import java.util.List;

@TestScoped
public class BrandManager {
    private final BrandCollector brandCollector;
    private final BrandNavigator brandNavigator;
    private final IPageScroller IPageScroller;

    @Inject
    public BrandManager(BrandCollector brandCollector,
                        BrandNavigator brandNavigator,
                        IPageScroller IPageScroller) {
        this.brandCollector = brandCollector;
        this.brandNavigator = brandNavigator;
        this.IPageScroller = IPageScroller;
    }


    public List<String> clickBrand(boolean found) {
        List<String> brandlist = new ArrayList<>();
        if (found) {
            WebElement brandDropdown = IPageScroller.scrollToField("ბრენდი *");
            if (brandDropdown != null) {
                brandDropdown.click();
                brandlist = brandCollector.collectBrandsFromDropdown();
               brandNavigator.backToCategories();
            } else {
                brandNavigator.returnToForm(true);
            }
        }
        return brandlist;
    }
}