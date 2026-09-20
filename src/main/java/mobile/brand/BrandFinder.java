package mobile.brand;

import com.google.inject.Inject;
import mobile.category.screen.ScreenReader;
import mobile.category.scroll.PageScroller;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;

@TestScoped
public class BrandFinder {

    private final PageScroller pageScroller;
    private final ScreenReader screenReader;
    private final BrandNavigator brandNavigator;

    @Inject
    public BrandFinder(PageScroller pageScroller,
                       ScreenReader screenReader,
                       BrandNavigator brandNavigator) {
        this.pageScroller = pageScroller;
        this.screenReader = screenReader;
        this.brandNavigator = brandNavigator;
    }

    public boolean findBrandDropdown() {
        boolean found = false;
        boolean characteristicsOpened = false;

        try {
            WebElement maxasiatebeli = pageScroller.scrollToField("მახასიათებლები *");
            if (maxasiatebeli != null) {
                screenReader.read();
                maxasiatebeli.click();
                characteristicsOpened = true;

                WebElement branddropdown = pageScroller.scrollToField("ბრენდი *");
                if (branddropdown != null && branddropdown.isDisplayed()) {
                    found = true;
                } else {
                    System.out.println("⚠️ ბრენდის ველი ვერ მოიძებნა, ვბრუნდებით კატეგორიებში...");
                }
            } else {
                System.out.println("⚠️ მახასიათებლები ვერ მოიძებნა, ვბრუნდებით კატეგორიებში...");
            }
        } catch (Exception e) {
            System.out.println("ბრენდი ვერ მოიძებნა: " + e.getMessage());
        }

        if (!found) {
            brandNavigator.returnToForm(characteristicsOpened);
        }
        return found;
    }
}